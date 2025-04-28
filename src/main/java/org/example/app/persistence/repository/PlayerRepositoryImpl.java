package org.example.app.persistence.repository;

import org.example.app.persistence.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements IPlayerRepository{

   public List<Player> playerDataBase = new ArrayList<>(List.of(
            new Player(1L, "Lionel Messi", "Inter Miami", "Delantero"),
            new Player(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
            new Player(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
            new Player(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
            new Player(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
            new Player(6L, "Virgil van Dijk", "Liverpool", "Defensa")
    ));


    @Override
    public List<Player> findAll() {
        System.out.println("--> Metodo findAll real");
        return this.playerDataBase;
    }

    @Override
    public Player findById(Long id) {
        System.out.println("--> Metodo findById real");
        return this.playerDataBase.stream()
                .filter(player -> player.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void savePlayer(Player player) {
        System.out.println("--> Metodo save real");
        this.playerDataBase.add(player);

    }

    @Override
    public void deleteById(Long id) {
        System.out.println("--> Metodo delete real");
        this.playerDataBase = this.playerDataBase.stream().filter(player -> player.getId() != id).toList();

    }


}
