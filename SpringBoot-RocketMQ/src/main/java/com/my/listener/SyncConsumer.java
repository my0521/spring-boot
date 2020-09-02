package com.my.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 同步消费
 */
@Service
@RocketMQMessageListener(consumerGroup = "my-consumer_sync-topic", topic = "sync-topic")
public class SyncConsumer implements RocketMQListener<MessageExt> {

    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        System.out.println(new String(body));
    }
}