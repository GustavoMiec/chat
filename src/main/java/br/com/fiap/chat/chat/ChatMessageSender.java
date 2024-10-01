package br.com.fiap.chat.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageSender(RabbitTemplate rabbitTemplate, SimpMessagingTemplate messagingTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String message) {
        // Envia para RabbitMQ
        rabbitTemplate.convertAndSend("chat-queue", message);

        // Envia para WebSocket
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
