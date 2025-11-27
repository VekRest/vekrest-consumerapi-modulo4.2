package com.vekrest.vekconsumerapi.vekconsumerapi.consumer;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Client;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.interfaces.VekClientIntegration;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class ClientRegisteredConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ClientRegisteredConsumer.class);

    private final VekClientIntegration vekClientIntegration;
    private final boolean vekClientApiEnabled;

    ClientRegisteredConsumer(
            VekClientIntegration vekClientIntegration,
            @Value("${vekrest.vekclient.api.enabled}") boolean vekClientApiEnabled
    ) {
        this.vekClientIntegration = vekClientIntegration;
        this.vekClientApiEnabled = vekClientApiEnabled;
    }

    @RetryableTopic(
            autoCreateTopics = "false",
            backoff = @Backoff(
                    delay = 15000,
                    multiplier = 2.0,
                    maxDelay = 54000
            ),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
            topics = "${spring.kafka.topic.client.registered}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listener(@Payload ConsumerRecord<String, Client> consumerRecord) {
        LOG.info("VEKCONSUMERAPI -> Um cliente deseja realizar cadastro: {}!", consumerRecord.value().toString());

        if(consumerRecord.headers().lastHeader("TOKEN") != null){
            final String token = new String(consumerRecord.headers().lastHeader("TOKEN").value());
            LOG.info("Token de autenticação recebido no cabeçalho da mensagem Kafka.");

            if(vekClientApiEnabled){
                LOG.info("A integração com a VekSecurity está habilitada. Iniciando registro do cliente na VekClient.");
                var registeredClient = vekClientIntegration.registerClient(consumerRecord.value(), token);
                LOG.info("Cliente registrado com sucesso na VekClient: {}", registeredClient.toString());
            }
        }
    }
}