package cn.hzp.product.controller;

import cn.hzp.product.domain.Product;
import cn.hzp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(id != 1) {
            throw  new  RuntimeException("服务器异常");
        }
        Product product = productService.findById(id);
        return product;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String save(@RequestBody Product product) {
        productService.save(product);
        return "保存成功";
    }
}
