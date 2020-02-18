package org.sc.service;

import org.sc.fallbackFactory.ScheduleServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order", fallbackFactory = ScheduleServiceFallbackFactory.class)
public interface ScheduleService {

	/**
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/order/getOrderInfo")
	public String callOrderService(@RequestParam(value = "name") String name);
	
}
