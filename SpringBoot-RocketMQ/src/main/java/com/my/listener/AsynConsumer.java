package com.my.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


@Service
@RocketMQMessageListener(consumerGroup = "my-consumer_asyn-topic", topic = "asyn-topic")
public class AsynConsumer implements RocketMQListener<MessageExt> {

	@Override
	public void onMessage(MessageExt message) {
		 byte[] body = message.getBody();
	     System.out.println(new String(body));		
	}

}
