package br.com.db1globalsoftaware.kafka;

import br.com.db1globalsoftaware.domain.Message;
import br.com.db1globalsoftaware.infrastructure.config.KafkaTopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaTopicConfig.STRING_QUEUE, groupId = KafkaConsumerConfig.GROUP_NAME)
    public void stringConsumer(String message) {
        logger.info("====> Consumiu mensagem: '{}'", message);
    }

    @KafkaListener(topics = KafkaTopicConfig.OBJECT_QUEUE, groupId = KafkaConsumerConfig.GROUP_NAME)
    public void messageConsumer(Message message) {
        logger.info("====> Consumiu mensagem: '{}'", message);
    }

}
