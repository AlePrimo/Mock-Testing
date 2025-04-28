package org.example.app.persistence.repository;

import org.example.app.persistence.entity.Player;

import java.util.List;

public interface IPlayerRepository {

    List<Player> findAll();
    Player findById(Long id);
    void savePlayer (Player player);
    void deleteById(Long id);


}
