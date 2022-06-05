package io.ozmani.notification;

import io.ozmani.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication(
      scanBasePackages = {
              "io.ozmani.notification",
              "io.ozmani.amqp"
      }
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);

    }

    // Example published message through commandline runner.
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
//                                        NotificationConfig notificationConfig) {
//        return  args -> {
//            producer.publish(new Person("Jake", 32),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Person(String name, int age){}
}
