package cn.hzp.order.controller;

import cn.hzp.order.command.OrderCommand;
import cn.hzp.order.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
public class OrderController {
    //注入restTemplate对象
    @Autowired
    private RestTemplate restTemplate;


   @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        Product product =new OrderCommand(restTemplate,id).execute();
        //Product product = restTemplate.getForObject("http://localhost:9001/product/"+id,Product.class);
        return product;
    }

    /**
     * 压力测试用
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String findOrder(@PathVariable Long id) {
        System.out.println(Thread.currentThread().getName());
        return "压力测试-根据id查询订单"+id;
    }
}
