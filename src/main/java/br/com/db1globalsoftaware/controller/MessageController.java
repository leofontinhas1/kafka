package br.com.db1globalsoftaware.controller;

import br.com.db1globalsoftaware.domain.Message;
import br.com.db1globalsoftaware.infrastructure.config.KafkaTopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;


    @Autowired
    private KafkaTemplate<String, Message> messageKafkaTemplate;

    @GetMapping
    public void sendStringMessage(@RequestParam("message") String param) {
        logger.info("==> Recebido mensagem '{}'", param);
        stringKafkaTemplate.send(KafkaTopicConfig.STRING_QUEUE, param);
    }

    @GetMapping("/object")
    public void sendObjectMessage(@RequestParam("message") String param) {
        Message message = new Message(param);
        logger.info("==> Recebido mensagem '{}'", message);
        messageKafkaTemplate.send(KafkaTopicConfig.OBJECT_QUEUE, message);
    }

}
