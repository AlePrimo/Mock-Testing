package org.example.app.service;

import org.example.app.persistence.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();
    Player findById(Long id);
    void savePlayer (Player player);
    void deleteById(Long id);
}
