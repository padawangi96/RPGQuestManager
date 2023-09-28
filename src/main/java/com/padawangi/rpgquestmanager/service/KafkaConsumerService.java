/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.service;

/**
 *
 * @author Padawangi
 */
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "quest_completion", groupId = "my-group")
    public void listenQuestCompletion(String message) {
        System.out.println("Received Quest Completion Message: " + message);
    }
}
