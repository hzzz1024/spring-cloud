package cn.hzp.order.exception;

import cn.hzp.order.domain.Product;
import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class ExceptionUtils {

	/**
	 * 静态方法
	 *      返回值: SentinelClientHttpResponse
	 *      参数 : request , byte[] , clientRquestExcetion , blockException
	 */
	//限流熔断业务逻辑
	public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
		Product product=new Product();
		product.setProductName("限流熔断业务逻辑");
		//注意：返回值数据要与熔断接口对象json一致
		return new SentinelClientHttpResponse(JSON.toJSONString(product));
	}

	//异常降级业务逻辑
	public static SentinelClientHttpResponse handleFallback(HttpRequest request, byte[] body,
		ClientHttpRequestExecution execution, BlockException ex) {
		Product product=new Product();
		product.setProductName("异常降级业务逻辑");
		//注意：返回值数据要与熔断接口对象json一致
		return new SentinelClientHttpResponse(JSON.toJSONString(product));
	}

}
