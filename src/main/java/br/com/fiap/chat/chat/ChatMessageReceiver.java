package br.com.fiap.chat.chat;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageReceiver {

    private final List<String> messages = new ArrayList<>();

    @RabbitListener(queues = "chat-queue")
    public void receiveMessage(String message) {
        messages.add(message);
        System.out.println("Mensagem recebida: " + message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
