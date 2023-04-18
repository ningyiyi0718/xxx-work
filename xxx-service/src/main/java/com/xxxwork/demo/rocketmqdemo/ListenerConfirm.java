package com.xxxwork.demo.rocketmqdemo;

import com.xxxwork.demo.utils.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: ElevenYang
 * @Description:
 * @Date 2023/4/9 10:15
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = MqConstants.TEST_TOPIC_1, consumerGroup = MqConstants.TEST_CONSUMER_GROUP,
        selectorExpression = "*", consumeMode = ConsumeMode.CONCURRENTLY)
public class ListenerConfirm implements RocketMQListener<Object>, RocketMQPushConsumerLifecycleListener {

    private DefaultMQPushConsumer consumer;

    @Override
    public void onMessage(Object s) {

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
        this.consumer.setPullInterval(1000);
        this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        this.consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
            log.info("收到消息时间：{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            for (MessageExt ext : list) {
                log.info("============================================");
                log.info("收到消息了然后ack：{}", new String(ext.getBody()));
                log.info("============================================");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    }
}
