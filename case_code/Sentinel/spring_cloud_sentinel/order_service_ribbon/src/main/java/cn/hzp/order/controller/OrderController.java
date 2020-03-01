package cn.hzp.order.controller;

import cn.hzp.order.domain.Product;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @SentinelResource
     *      blockHandler : 声明熔断时调用的降级方法
     *      fallback : 抛出异常执行的降级方法
     *      value : 自定义的资源名称
     *          * 不设置:当前全类名.方法名
     */
    //@SentinelResource(value="orderFindById",blockHandler = "orderBlockHandler",fallback = "orderFallback")
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
	 * 定义降级逻辑
	 *  hystrix和sentinel区别
     *  hystrix只有一个降级方法，而sentinel更细致，如下：
	 *      1.熔断执行的降级方法
	 *      2.抛出异常执行的降级方法
	 */

    /**
     * 熔断执行的降级方法
     * @param id
     * @return
     */
	public Product orderBlockHandler(Long id) {
		Product product = new Product();
		product.setProductName("触发熔断的降级方法");
		return product;
	}

    /**
     * 抛出异常时的降级方法
     * @param id
     * @return
     */
	public Product orderFallback(Long id) {
		Product product = new Product();
		product.setProductName("抛出异常执行的降级方法");
		return product;
	}

}
