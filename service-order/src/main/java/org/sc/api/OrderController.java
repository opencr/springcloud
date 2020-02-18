package org.sc.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/order/")
@Api("订单信息相关接口")
public class OrderController {

	private static final Logger LOG = Logger.getLogger(OrderController.class.getName());

	@GetMapping("")
	public String index() {
		return "hello,i am order service";
	}
	/**
	 * name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
        paramType：参数放在哪个地方
            · header --> 请求参数的获取：@RequestHeader
            · query --> 请求参数的获取：@RequestParam
            · path（用于restful接口）--> 请求参数的获取：@PathVariable
            · body（不常用）
            · form（不常用）
	 * @param name
	 * @param age
	 * @return
	 */
	@ApiOperation(value = "获取订单信息", notes = "必填项有姓名、年龄，且不能超过100个字符")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户编号", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "orderId", value = "订单号", required = false, paramType = "form", dataType = "Integer") })
	@GetMapping("getOrderInfo")
	public String getOrderInfo(@RequestParam String userId, @RequestParam Integer orderId) {
		LOG.log(Level.INFO, "servuce b feign is being called!");
		return "hi" + userId + ", i am order service ";
	}

}
