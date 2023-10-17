/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.padawangi.rpgquestmanager.quest;

/**
 *
 * @author Padawangi
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padawangi.rpgquestmanager.player.Player;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Table(name = "quests")
@Document(indexName = "quest")
public class Quest {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quest_seq")
    @SequenceGenerator(name = "quest_seq", sequenceName = "quest_seq", allocationSize = 1)
    private Long id;
    
    @Field(type = FieldType.Text)
    private String name;
    
    @Field(type = FieldType.Text)
    private String description;
    
    private int rewardPoints;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "quests")
    private List<Player> players;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    
}