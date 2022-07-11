package xu.all.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * @Description: 绑定交换机和队列
     * TopicExchange：表示类型为 topic 的交换机
     * 若创建其他类型，则新建 DirectExchange 等对象
     */
    @PostMapping("/bind")
    public void bindExchangeAndQueue(@RequestParam("exchangeName") String exchangeName,
                                     @RequestParam("queueName") String queueName,
                                     @RequestParam("routingKeys") List<String> routingKeys) {
        log.info(">>> request rabbitmq bind exchange and queue, exchangeName[{}] queueName[{}] routingkeys[{}]", exchangeName, queueName, routingKeys);
        TopicExchange exchange = new TopicExchange(exchangeName, true, false);
        Queue queue = new Queue(queueName);

        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        routingKeys.forEach(routingKey -> amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKey)));
    }

    @GetMapping("/send")
    public void send(@RequestParam("exchange") String exchange,
                     @RequestParam("routingKey") String routingKey,
                     @RequestBody Object message) {
        log.info(">>> request rabbitmq send, exchange[{}] routingkey[{}] message[{}]", exchange, routingKey, message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    @GetMapping("/receive")
    public Object receive(@RequestParam("queue") String queue) {
        log.info(">>> request rabbitmq receive, queue[{}]", queue);
        return rabbitTemplate.receiveAndConvert(queue);
    }
}
