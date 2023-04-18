package com.xxxwork.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.xxxwork.demo.entity.TransMessage;
import com.xxxwork.demo.result.ResultData;
import com.xxxwork.demo.utils.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: ElevenYang
 * @Description: 用于测试rocketmq
 * @Date 2023/4/8 20:17
 */
@Slf4j
@RequestMapping("/message")
@RestController
public class MessageAct {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping(value = "/sendTest", method = RequestMethod.GET)
    public Object sendTest() throws Exception {
        rocketMQTemplate.convertAndSend(MqConstants.TEST_TOPIC_1, "hello world");
        return ResultData.success();
    }

    /**
     * 事务消息：
     * 1. 给指定uid的用户插入一条地址记录
     * 2. 随机分配一个金额给uid的用户作为余额
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendTrans", method = RequestMethod.GET)
    public Object sendTrans(Integer uid, String address) throws Exception {
        // 账户类型1000-001-001
        Integer type = 1000001001;
        // 随机0-100的金额
        int balance = new Random().nextInt(100);

        // 构建消息体
        String uuid = UUID.randomUUID().toString();
        TransMessage transMessage = new TransMessage(
                uuid, uid, address, type, new BigDecimal(balance), LocalDateTime.now()
        );

        log.info("===============事务消息发送信息：{}", JSONObject.toJSONString(transMessage));
        // 1. 发送事务消息
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(
                MqConstants.TEST_TRANS_TOPIC,
                MessageBuilder.withPayload(transMessage).build(),
                null
        );
        log.info("===============事务消息本地执行状态：{}, uuid: {}", result.getLocalTransactionState().name(), uuid);

        return ResultData.success();
    }

}
