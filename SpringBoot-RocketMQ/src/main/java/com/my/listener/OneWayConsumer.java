package com.my.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 单向消息
 */
@Service
@RocketMQMessageListener(consumerGroup = "my-consumer_oneWay-topic", topic = "oneWay-topic")
public class OneWayConsumer implements RocketMQListener<MessageExt> {

    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        System.out.println("oneWay-topic:" + new String(body));
    }
}