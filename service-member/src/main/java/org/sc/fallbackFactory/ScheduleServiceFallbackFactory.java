package org.sc.fallbackFactory;

import org.sc.service.ScheduleService;
import org.sc.service.factory.ScheduleServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 针对自身项目中每个模块新建一个FallbackFactory类以及一个Fallback接口，
 * 作用就是Hystrix服务降级（熔断）
 * 在Controller 和Feign核心接口ServiceFeign 中，如果方法参数是对象，
 * 一定要加@RequestBody注解，否则会报错，或者参数传递不进来，
 * 如果是基本数据类型或者string则用@RequesParam（value=" "）
 * @author liuhh
 *
 */

@Component
public class ScheduleServiceFallbackFactory implements FallbackFactory<ScheduleService> {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceFallbackFactory.class);
	@Override
	public ScheduleService create(Throwable cause) {
		return new ScheduleServiceFactory() {
			@Override
			public String callOrderService(String name) {
				ScheduleServiceFallbackFactory.logger.info("=====feign start call serviceB======");
				return "调用service-b服务失败，启动hystrix服务降级方案：首选方式";
			}
		};
	}

}
