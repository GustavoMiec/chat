package br.com.fiap.chat.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChatController {

    private final ChatMessageSender chatMessageSender;
    private final ChatMessageReceiver chatMessageReceiver;

    public ChatController(ChatMessageSender chatMessageSender, ChatMessageReceiver chatMessageReceiver) {
        this.chatMessageSender = chatMessageSender;
        this.chatMessageReceiver = chatMessageReceiver;
    }

    // Método para exibir a página de chat
    @GetMapping("/chat")
    public String getChatPage(Model model) {
        model.addAttribute("messages", chatMessageReceiver.getMessages()); // Adiciona as mensagens à view
        return "chat";  // Nome do arquivo HTML (chat.html) que será exibido
    }

    // Método para enviar uma mensagem
    @PostMapping("/send")
    public String sendMessage(String username, String message, String gender, RedirectAttributes redirect) {
        String formattedMessage = username + ": " + message;
        chatMessageSender.sendMessage(formattedMessage);
        redirect.addFlashAttribute("username", username);
        redirect.addFlashAttribute("gender", gender);
        return "redirect:/chat";
    }
}
