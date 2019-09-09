package br.com.db1globalsoftaware.infrastructure.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaPropertiesTest {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    public void shouldReturnBootstrapAddress() {
        assertEquals("192.168.99.100:9092", kafkaProperties.getBootstrapAddress());
    }
}