package com.vet.apigateway.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic () {
        Map<String, String> configuration = new HashMap<>();
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //* TopicConfig.CLEANUP_POLICY_DELETE -> borra el mensaje, si despues de cierto tiempo no se usa
        //* TopicConfig.CLEANUP_POLICY_COMPACT -> mantiene el mas actual, si mando un mensaje hola con cierto valor y luego envio otro, mantiene el ultimo
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        //* cuando envian un mensaje se retiene esa cantidad de tiempo, luego o se elimina o se mantiene el ultimo como indicamos antes
        //! por defecto viene en valor -1, no se borran los mensajes
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        // * tamaño maximo del segmento en bytes, por defecto 1GB
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1024");
        //* tamaño maximo de cada msg, por defecto 1MB
        //configuracion basica, normalmente hay mas
        return TopicBuilder
                .name("prueba_topic")
                .partitions(2)
                .replicas(2) //replicas por si se cae el topic
                .configs(configuration)
                .build();
    }
}
