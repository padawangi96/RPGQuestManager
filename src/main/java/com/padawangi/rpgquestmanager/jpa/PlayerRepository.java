/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.padawangi.rpgquestmanager.jpa;

/**
 *
 * @author Padawangi
 */
import com.padawangi.rpgquestmanager.player.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(value = "SELECT * FROM players p JOIN player_quest pq ON p.id = pq.player_id WHERE pq.quest_id = :questId", nativeQuery = true)
    List<Player> findAllByCompletedQuest(@Param("questId") Long questId);

    @Query(value = "SELECT * FROM players ORDER BY points DESC LIMIT 5", nativeQuery = true)
    List<Player> findTopPlayersByPoints();
}
