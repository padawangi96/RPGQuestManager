/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.player;

/**
 *
 * @author Padawangi
 */
import com.padawangi.rpgquestmanager.player.Player;
import com.padawangi.rpgquestmanager.quest.Quest;
import com.padawangi.rpgquestmanager.jpa.PlayerRepository;
import com.padawangi.rpgquestmanager.jpa.QuestRepository;
import com.padawangi.rpgquestmanager.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class PlayerService {
    
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private QuestRepository questRepository;

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Cacheable(value = "players", key = "#playerId")
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Player not found with ID: " + id));
    }
    
    @CachePut(value = "players", key = "#player.id")
    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }
    
    @CacheEvict(value = "players", key = "#playerId")
    public void deletePlayer(Long playerId) {
        playerRepository.deleteById(playerId);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    public Player embarkOnQuest(Long playerId, Long questId) {
        Player player = playerRepository.findById(playerId).orElseThrow(
            () -> new RuntimeException("Player not found!")
        );
        
        Quest quest = questRepository.findById(questId).orElseThrow(
            () -> new RuntimeException("Quest not found!")
        );

        player.getQuests().add(quest);
        return playerRepository.save(player);
    }

    public Player completeQuest(Long playerId, Long questId) {
        Player player = playerRepository.findById(playerId).orElseThrow(
            () -> new RuntimeException("Player not found!")
        );
        
        Quest quest = questRepository.findById(questId).orElseThrow(
            () -> new RuntimeException("Quest not found!")
        );

        if (player.getQuests().contains(quest)) {
            player.setPoints(player.getPoints() + quest.getRewardPoints());
            player.getQuests().remove(quest);
            
            String message = "Player " + player.getName() + " has completed quest: " + quest.getName();
            kafkaProducerService.sendQuestCompletionMessage(message);
        
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Player hasn't embarked on this quest!");
        }
    }
    
    public List<Player> getPlayersByCompletedQuest(Long questId) {
        return playerRepository.findAllByCompletedQuest(questId);
    }

    public List<Player> getTopPlayersByPoints() {
        return playerRepository.findTopPlayersByPoints();
    }

}