package com.example.rabbitqs.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;

import java.util.HashMap;
import java.util.Map;

@Service
public class PatientMessageListener {
    private static final Logger log = LoggerFactory.getLogger(PatientMessageListener.class);

    @RabbitListener(queues = "generic_queue")
    public void receiveMessageG(final Message message){

        log.info("received msg: {}"+message.toString());
       /* String charsertName = "UTF-8";
        String msgDecoded = "default";
        try{
           msgDecoded = new String(message.getBody(), charsertName);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        log.info("Received message "+ msgDecoded);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualNode = mapper.readTree(msgDecoded);
            JsonNode activity = actualNode.get("activity");
            log.info("activity "+ activity);
        }catch(Exception e){
            e.printStackTrace();
        }*/

    }
}
