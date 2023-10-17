package com.padawangi.rpgquestmanager.player;

/**
 *
 * @author Padawangi
 */
import com.padawangi.rpgquestmanager.quest.Quest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "player_seq", allocationSize = 1)
    private Long id;
    private String name;
    private int points;
    
    @ManyToMany
    @JoinTable(
        name = "PLAYER_QUEST",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "quest_id")
    )
    private List<Quest> quests;

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public Long getId(){
        return this.id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
}