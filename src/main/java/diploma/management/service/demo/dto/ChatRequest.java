package diploma.management.service.demo.dto;

import java.util.List;

//public class ChatRequest {
//    private List<Message> messages;
//    private double temperature;
//    private int max_tokens;
//
//    public ChatRequest() {}
//
//    public ChatRequest(List<Message> messages, double temperature, int max_tokens) {
//        this.messages = messages;
//        this.temperature = temperature;
//        this.max_tokens = max_tokens;
//    }
//
//    public List<Message> getMessages() {
//        return messages;
//    }
//
//    public double getTemperature() {
//        return temperature;
//    }
//
//    public int getMax_tokens() {
//        return max_tokens;
//    }
//
//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }
//
//    public void setTemperature(double temperature) {
//        this.temperature = temperature;
//    }
//
//    public void setMax_tokens(int max_tokens) {
//        this.max_tokens = max_tokens;
//    }
//}
public class ChatRequest {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

