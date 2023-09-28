/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.padawangi.rpgquestmanager.repository;

import com.padawangi.rpgquestmanager.quest.Quest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *
 * @author Padawangi
 */
public interface QuestElasticSearchRepository extends ElasticsearchRepository<Quest, Long> {
    
}
