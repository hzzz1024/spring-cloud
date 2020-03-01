package cn.hzp.order.controller;

import cn.hzp.order.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    //注入restTemplate对象
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 注入DiscoveryClient :
     *  springcloud提供的获取元数据的工具类
     *      调用方法获取服务的元数据信息
     *
     */
    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 基于Eureka元数据方式DiscoveryClient调用服务
     * @param id
     * @return
     */
   @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {

        //已调用服务名称获取所有的元数据
		List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
		//获取唯一的一个元数据
		ServiceInstance instance = instances.get(0);
		//根据元数据中的主机地址和端口号拼接请求微服务的URL
		//如何调用商品服务?
        Product product = restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/"+id,Product.class);

        //Product product = restTemplate.getForObject("http://localhost:9001/product/"+id,Product.class);
        return product;
    }
}
