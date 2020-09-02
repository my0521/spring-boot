package com.my.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.rocketmq.Producer;

@RestController
public class MQProducerController {
	@Autowired
	private Producer producer;

	@RequestMapping(value = "/SendOneWay", method = RequestMethod.POST)
	public String SendOneWay(@RequestBody  String msg) {
		Message message = new Message();
		message.setBody(msg.getBytes());
		producer.SendOneWay(message);
		return "success";
	}

	@RequestMapping(value = "/AsynNews", method = RequestMethod.POST)
	public String AsynNews(@RequestBody String msg) {
		Message message = new Message();
		message.setBody(msg.getBytes());
		producer.AsynNews(message);
		return "success";
	}

	@RequestMapping(value = "/SyncNews", method = RequestMethod.POST)
	public String SyncNews(@RequestBody String msg) {
		Message message = new Message();
		message.setBody(msg.getBytes());
		producer.SyncNews(message);
		return "success";
	}

}
