/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.quest;

/**
 *
 * @author Padawangi
 */
//import com.padawangi.rpgquestmanager.repository.QuestElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quests")
public class QuestController {

    @Autowired
    QuestService questService;
    
//    @Autowired
//    QuestElasticSearchRepository questElasticsearchRepository;

    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.saveQuest(quest);
    }

    @GetMapping("/{id}")
    public Quest getQuest(@PathVariable Long id) {
        return questService.getQuestById(id).orElse(null);
    }

    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @GetMapping("/search")
    public Iterable<Quest> searchQuests(@RequestParam String query) {
        return questService.searchQuests(query);
    }

}
