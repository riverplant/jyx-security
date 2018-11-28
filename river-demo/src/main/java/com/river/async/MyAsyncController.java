package com.river.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 
 * @author riverplant
 *
 */
@RestController
@RequestMapping("/order")
public class MyAsyncController {
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping
	public DeferredResult<String> order() throws Exception {
		logger.info("start...");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);

		DeferredResult<String> result = new DeferredResult<String>();

		
		deferredResultHolder.getMap().put(orderNumber, result);

		// Callable<String> result = new Callable<String>() {
		//
		// @Override
		// public String call() throws Exception {
		// logger.info(" call start...");
		// Thread.sleep(1000);
		// logger.info(" call end...");
		// return "success";
		// }
		// };
		logger.info("end...");

		return result;
	}
}
