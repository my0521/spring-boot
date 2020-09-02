package com.my.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	/**
	 * 同步消息
	 */
	public SendResult SyncNews(Message message ) {
		SendResult sendResult = rocketMQTemplate.syncSend("sync-topic", message);
		// 同步消息发送成功会有一个返回值，我们可以用这个返回值进行判断和获取一些信息
		System.out.println(sendResult);
		return sendResult;
	}

	/**
	 * 异步消息
	 */

	public String AsynNews(Message message) {
		String ret = "";
		rocketMQTemplate.asyncSend("asyn-topic", message, new SendCallback() {
			@Override
			public void onSuccess(SendResult sendResult) {
				System.out.println(sendResult.toString());
			}

			@Override
			public void onException(Throwable e) {
				e.printStackTrace();
			}
		});
		return ret;
	}

	/**
	 * 单向消息
	 */

	public boolean SendOneWay(Message message) {
		rocketMQTemplate.sendOneWay("oneWay-topic", message);
		return true;
	}
}
