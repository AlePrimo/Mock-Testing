package org.example.app.persistence.repository;

import org.example.app.persistence.entity.Player;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class PlayerRepositoryImplTest {

    private PlayerRepositoryImpl playerRepository;

    @BeforeEach
    public void init(){
        this.playerRepository = new PlayerRepositoryImpl();
    }

    @Test
    public void testFindAll(){
        List<Player> playerList = this.playerRepository.findAll();
        assertNotNull(playerList);

    }


    @Test
    public void testFindById(){
        Long id = 1L;

        Player player = this.playerRepository.findById(id);

        assertNotNull(player);
        assertEquals(id, player.getId());


    }

    @Test
    public void testFindByIdNotFound(){
        Long id = 111L;
        assertThrows(NoSuchElementException.class, ()->{this.playerRepository.findById(id);});

    }


    @Test
    public void testSavePlayer() {
      Player player = new Player(7L,"PEPE","CARONCHIA", "MEDIO");
      int dataSizeBefore = this.playerRepository.playerDataBase.size();
      this.playerRepository.savePlayer(player);
      int dataSizeAfter = this.playerRepository.playerDataBase.size();
      assertEquals(dataSizeAfter,dataSizeBefore + 1);

    }

    @Test
    public void testDeleteById() {
        Long id = 6L;
        int dataSizeBefore = this.playerRepository.playerDataBase.size();
        this.playerRepository.deleteById(id);
        int dataSizeAfter = this.playerRepository.playerDataBase.size();
        assertEquals(dataSizeAfter,dataSizeBefore - 1);

    }





}
