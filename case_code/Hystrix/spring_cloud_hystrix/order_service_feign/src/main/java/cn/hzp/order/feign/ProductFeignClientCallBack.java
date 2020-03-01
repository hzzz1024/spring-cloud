package cn.hzp.order.feign;

import cn.hzp.order.domain.Product;
import org.springframework.stereotype.Component;

/**
 * 实现FeignClient的接口，作为降级方法使用
 */
@Component
public class ProductFeignClientCallBack implements ProductFeignClient {

	/**
	 * 熔断降级的方法
	 */
	public Product findById(Long id) {
		Product product = new Product();
		product.setProductName("feign调用触发熔断降级方法");
		return product;
	}
}
