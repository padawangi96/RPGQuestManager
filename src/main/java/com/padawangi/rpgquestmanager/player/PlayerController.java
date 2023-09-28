/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.player;

/**
 *
 * @author Padawangi
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
    @PostMapping("/{playerId}/embark/{questId}")
    public Player embarkOnQuest(@PathVariable Long playerId, @PathVariable Long questId) {
        return playerService.embarkOnQuest(playerId, questId);
    }

    @PostMapping("/{playerId}/complete/{questId}")
    public Player completeQuest(@PathVariable Long playerId, @PathVariable Long questId) {
        return playerService.completeQuest(playerId, questId);
    }
    
    @GetMapping("/byQuest/{questId}")
    public List<Player> getPlayersByCompletedQuest(@PathVariable Long questId) {
        return playerService.getPlayersByCompletedQuest(questId);
    }

    @GetMapping("/topPlayers")
    public List<Player> getTopPlayersByPoints() {
        return playerService.getTopPlayersByPoints();
    }

}
