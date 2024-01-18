package tech.inno.demodeanery.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MyMessageReceiver {
    @RabbitListener(queues = "createStudentQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message" + message);
    }
}
