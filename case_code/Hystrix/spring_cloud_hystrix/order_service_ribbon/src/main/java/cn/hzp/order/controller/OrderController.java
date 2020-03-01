package cn.hzp.order.controller;

import cn.hzp.order.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
/**
 * @DefaultProperties : 指定此接口中公共的熔断设置
 *      如果过在@DefaultProperties指定了公共的降级方法
 *      在@HystrixCommand不需要单独指定了
 */
//@DefaultProperties(defaultFallback = "defaultFallBack")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用注解配置熔断保护
     *     fallbackmethod : 配置熔断之后的降级方法
     */
    @HystrixCommand(fallbackMethod = "orderFallBack")
   // @HystrixCommand
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        if(id != 1) {
            throw  new  RuntimeException("服务器异常");
        }
        /**
         * 基于ribbon的形式调用远程微服务
         *  1.使用@LoadBalanced声明RestTemplate
         *  2.使用服务名称service-product替换ip地址
         */
        Product product = restTemplate.getForObject("http://service-product/product/"+id,Product.class);
        return product;
    }

    /**
     * 指定统一的降级方法
     *  * 参数 : 没有参数
     */
    public Product defaultFallBack() {
        Product product = new Product();
        product.setProductName("触发统一的降级方法");
        return product;
    }

    /**
     * 降级方法
     *  和需要收到保护的方法的返回值一致
     *  方法参数一致
     */
    public Product orderFallBack(Long id) {
        Product product = new Product();
        product.setProductName("触发降级方法");
        return product;
    }

}
