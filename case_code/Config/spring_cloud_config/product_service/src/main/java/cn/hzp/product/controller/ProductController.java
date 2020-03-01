package cn.hzp.product.controller;

import cn.hzp.product.domain.Product;
import cn.hzp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RefreshScope //开启动态刷新
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${profile-name}")
    private String profileName;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return product;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return profileName;
    }
}
