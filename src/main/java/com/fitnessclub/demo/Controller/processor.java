package com.fitnessclub.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fitnessclub.demo.Entity.Information;
import com.fitnessclub.demo.Service.UIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class processor {

    private static final Logger LOG = LoggerFactory.getLogger("SimpleKafkaApplication");

    @Value("${message.topic.name}")
    private String topicName;


    @Autowired
    public UIservice service;

    private final KafkaTemplate<String, Information> kafkaTemplate;

    @Autowired
    public processor(KafkaTemplate<String, Information> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public String post(Information info){
        kafkaTemplate.send(topicName, info);
        return "请等待，您的信息正在保存";
    }


    @KafkaListener(topics = "information", groupId = "jcg-group")
    public void listen(Information message) throws InterruptedException {
        LOG.info("Received message in JCG group: {}", message.toString());
        Thread.sleep(60000);
        service.save(message);
    }

}
