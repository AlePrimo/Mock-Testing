package org.example.app.service;

import org.example.app.persistence.entity.Player;

import org.example.app.persistence.repository.PlayerRepositoryImpl;

import java.util.List;

public class PlayerServiceImpl implements IPlayerService{


    private PlayerRepositoryImpl playerRepository;

    public PlayerServiceImpl (PlayerRepositoryImpl playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        return this.playerRepository.findById(id);
    }

    @Override
    public void savePlayer(Player player) {
        this.playerRepository.savePlayer(player);

    }

    @Override
    public void deleteById(Long id) {
        this.playerRepository.deleteById(id);

    }
}
