/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.quest;

/**
 *
 * @author Padawangi
 */
import com.padawangi.rpgquestmanager.quest.Quest;
//import com.padawangi.rpgquestmanager.repository.QuestElasticSearchRepository;
import com.padawangi.rpgquestmanager.jpa.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;
    
//    @Autowired
//    QuestElasticSearchRepository questElasticsearchRepository;
    
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    public Quest createQuest(Quest quest) {
        if(quest.getId() != null && questRepository.existsById(quest.getId())) {
            throw new RuntimeException("Quest with ID " + quest.getId() + " already exists.");
        }
        Quest savedQuest = questRepository.save(quest);
        //questElasticsearchRepository.save(savedQuest);
        return savedQuest;
    }
    
    public Quest saveQuest(Quest quest) {
        Quest savedQuest = questRepository.save(quest);
        //questElasticsearchRepository.save(savedQuest);
        return savedQuest;
    }

    public Optional<Quest> getQuestById(Long id) {
        return questRepository.findById(id);
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }
    
    public List<Quest> searchQuests(String query) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.queryStringQuery(query))
            .build();

        SearchHits<Quest> searchHits = elasticsearchTemplate.search(searchQuery, Quest.class);
        return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}