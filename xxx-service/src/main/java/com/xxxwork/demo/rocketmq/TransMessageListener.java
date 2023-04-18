package com.xxxwork.demo.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.xxxwork.demo.entity.TransMessage;
import com.xxxwork.demo.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: ElevenYang
 * @Description: 监听本地事务执行的状态和检查本地事务状态
 * @Date 2023/4/17 23:29
 */
@Slf4j
@RocketMQTransactionListener
@Component
public class TransMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 执行本地事务（在发送消息成功后执行）
     * @param message
     * @param o
     * @return RocketMQLocalTransactionState
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 2. 执行本地事务
        // TODO 获取消息，加锁，防止幂等

        TransMessage msg = this.getTransMessage(message);
        log.info("===============事务消息本地开始执行，uuid：{}", msg.getTransId());

        int count = userAddressService.addUserAddress(msg.getUid(), msg.getAddress());
        if (count != 1) {
            log.info("===============事务消息本地执行失败，uuid：{}", msg.getTransId());
            return RocketMQLocalTransactionState.UNKNOWN;
        } else {
            log.info("===============事务消息本地执行成功，uuid：{}", msg.getTransId());
            // 3. 提交本地事务状态
            return RocketMQLocalTransactionState.COMMIT;
        }
    }

    /**
     * MQ回查本地事务的状态，防止网络中断，超时提交失败
     * @param message 最多重试15次，超过了默认丢弃此消息
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // 4. 检查本地事务状态
        TransMessage msg = this.getTransMessage(message);
        log.info("===============事务消息本地回查，uuid：{}", msg.getTransId());

        // TODO 获取消息数据，没有则新增，有则返回成功

        // 5. 提交本地事务状态
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    private TransMessage getTransMessage(Message message) {
        byte[] payload = (byte[]) message.getPayload();
        String data = new String(payload);
        try {
            return JSONObject.parseObject(data, TransMessage.class);
        } catch (Exception e) {
            log.error("===============事务消息本地解析失败：{}", data);
            return null;
        }
    }
}
