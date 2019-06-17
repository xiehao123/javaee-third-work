package cn.edu.bjtu.ming.controller;

import cn.edu.bjtu.ming.Entity.Information;
import cn.edu.bjtu.ming.Service.UIservice;
import cn.edu.bjtu.ming.vo.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/v1")
public class ProcessorControlleer {

    private static final Logger LOG = LoggerFactory.getLogger("SimpleKafkaApplication");

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;


    @Autowired
    public UIservice service;

    private final KafkaTemplate<String, Information> kafkaTemplate;

    @Autowired
    public ProcessorControlleer(KafkaTemplate<String, Information> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping(value = "/information",method = RequestMethod.POST)
    public HttpEntity<Status> post(Information info){
        Status status;
        try {
            kafkaTemplate.send(topicName, info);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }


    @KafkaListener(topics = "information", groupId = "jcg-group")
    public HttpEntity<Information> listen(Information message) throws InterruptedException {
        LOG.info("Received message in JCG group: {}", message.toString());
        Thread.sleep(6000);
        service.save(message);
        return  new ResponseEntity<Information>(message, HttpStatus.OK);
    }
}
