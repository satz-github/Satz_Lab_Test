package com.microservice.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ConsumerController {
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="callOnUnavailable",commandKey="consumer")
	@GetMapping("/callServer")
	public String callServer(){
	
	return restTemplate.getForObject("http://hello-producer/callService", String.class);
	
}
	public String callOnUnavailable(){
		return "Sorry Service Not available";
	}
	

}
