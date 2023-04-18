package com.xxxwork.demo.rocketmqdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author: ElevenYang
 * @Description: rocketmq消费者，顺序消息
 * @Date 2023/4/9 10:06
 */
@Slf4j
//@Component
public class ListenerOrderly implements MessageListenerOrderly {

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
        for (MessageExt ext : list) {
            log.info("============================================");
            log.info("收到顺序消息了：{}", new String(ext.getBody()));
            log.info("============================================");
        }
        return ConsumeOrderlyStatus.ROLLBACK;
    }
}
