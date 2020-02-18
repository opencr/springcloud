package org.sc.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.sc.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/member/")
@Api("会员信息相关接口")
public class MemberController {

	private static final Logger LOG = Logger.getLogger(MemberController.class.getName());

	@GetMapping("")
	public String index() {
		return "hello,i am member service";
	}
	
	@Value("${server.port}")
	private String port;
	@Autowired
	private ScheduleService scheduleService;
	@ApiOperation(value = "获取会员信息", notes = "必填项有姓名、年龄，且不能超过100个字符")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "age", value = "年龄", required = false, paramType = "form", dataType = "Integer") })
	
	@GetMapping("getMemberInfo")
	public String hi(@RequestParam String name, @RequestParam Integer age) {
		LOG.log(Level.INFO, "service menmber app feign is being called");
		return "hi " + name + ", i am member service";
	}
	
	@GetMapping("callOrderService")
	public String call(@RequestParam String name) {
		LOG.log(Level.INFO, "service member app feign is being called");
		return "-->"+scheduleService.callOrderService(name);
	}
	
}
