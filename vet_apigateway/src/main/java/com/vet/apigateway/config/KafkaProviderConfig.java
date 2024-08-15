package com.vet.apigateway.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    //configurar proveedor de mensajes de kafka
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //el de kafka, para convertir string a bytes y enviarlo a kafka
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // para serializar el valor
        return properties;
    }


    // lo que nos proveera el cliente para enviar los mensajes
    @Bean
    ProducerFactory<String, String> providerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    //el template para enviar los mensajes
    @Bean
    KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
