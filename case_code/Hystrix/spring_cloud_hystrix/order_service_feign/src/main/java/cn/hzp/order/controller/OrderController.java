package cn.hzp.order.controller;

import cn.hzp.order.domain.Product;
import cn.hzp.order.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 通过Feign方式，声明式调用接口
     */
    //@GetMapping(value = "/buy/{id}")
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        //调用接口实现调用商品服务。本质上还是调用restTemplate的方式，feign会进行转化
        Product product= productFeignClient.findById(id);
        // Product product = restTemplate.getForObject("http://service-product/product/"+id,Product.class);
        return product;
    }

}
