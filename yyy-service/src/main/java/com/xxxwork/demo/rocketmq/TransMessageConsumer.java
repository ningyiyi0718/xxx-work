package com.xxxwork.demo.rocketmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxwork.demo.entity.TransMessage;
import com.xxxwork.demo.service.AccountService;
import com.xxxwork.demo.utils.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Author: ElevenYang
 * @Description: 服务A本地事务执行成功，消费消息
 * @Date 2023/4/17 23:14
 */
@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = MqConstants.TEST_TRANS_CONSUMER_GROUP,
        topic = MqConstants.TEST_TRANS_TOPIC,
        messageModel = MessageModel.CLUSTERING
)
public class TransMessageConsumer implements RocketMQListener<TransMessage>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private AccountService accountService;

    @Override
    public void onMessage(TransMessage o) {

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 6. 监听本地事务提交成功，执行消费逻辑
        consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
            for (MessageExt ext : list) {
                boolean result = this.handleMessage(ext);
                if (!result) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    }

    private boolean handleMessage(MessageExt ext) {
        TransMessage transMessage = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            transMessage = mapper.readValue(new String(ext.getBody()), TransMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.info("===============事务消息消费者解析失败：{}", e.getMessage(), e);
        }

        if (ObjectUtils.isEmpty(transMessage)) {
            return false;
        }

        log.info("===============事务消息消费者获取消息：{}", transMessage);

        // TODO 加锁，幂等判断

        // TODO 消息超过多久没有被消费成功，报警需要处理

        int count = accountService.addUserAccount(transMessage.getUid(), transMessage.getType(), transMessage.getBalance());
        if (count != 1) {
            log.info("===============事务消息消费者执行失败，uuid：{}", transMessage.getTransId());
            return false;
        } else {
            log.info("===============事务消息消费者执行成功，uuid：{}", transMessage.getTransId());
            return true;
        }
    }
}
