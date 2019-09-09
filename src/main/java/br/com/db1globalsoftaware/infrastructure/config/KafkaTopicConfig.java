package br.com.db1globalsoftaware.infrastructure.config;

import br.com.db1globalsoftaware.infrastructure.properties.KafkaProperties;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    public static final String STRING_QUEUE = "string-queue";

    public static final String OBJECT_QUEUE = "object-queue";

    @Autowired
    private KafkaProperties properties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic stringQueue() {
        return new NewTopic(STRING_QUEUE, 1, (short) 1);
    }

    @Bean
    public NewTopic objectQueue() {
        return new NewTopic(OBJECT_QUEUE, 1, (short) 1);
    }
}
