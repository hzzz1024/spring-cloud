package cn.hzp.order.controller;

import cn.hzp.order.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {//注入restTemplate对象
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        Product product = restTemplate.getForObject("http://localhost:9001/product/"+id,Product.class);
        return product;
    }

}
