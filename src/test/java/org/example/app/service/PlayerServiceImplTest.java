package org.example.app.service;

import org.example.app.DataProvider;
import org.example.app.persistence.entity.Player;
import org.example.app.persistence.repository.PlayerRepositoryImpl;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

//IMPORTANTE!!  MOCKITO TRABAJA CON LAS DEPENDENCIAS Y NO CON LOS OBJETOS QUE ESTAMOS TESTEANDO, O SEA, EN ESTE CASO
    // LA CLASE PLAYERSERVICE DEPENDE DE PLAYERREPOSITORY, ESA ES SU DEPENDENCIA , Y MOCKITO (CREA OBJETOS  DE TIPO MOCK O SIMULACIONES)
    // VA A TRABAJAR CON PLAYER REPOSITORY

//ESTA ES UNA FORMA DE CREAR LOS OBJETOS NECESARIOS PARA LOS TEST
//    private PlayerRepositoryImpl playerRepository;
//    private PlayerServiceImpl playerService;
//
//    @BeforeEach
//    void init(){
//        this.playerRepository = mock(PlayerRepositoryImpl.class);
//        this.playerService = new PlayerServiceImpl(this.playerRepository);
//    }


    //ESTA ES OTRA FORMA (CON LAS ANOTACIONES DE MOCKITO)

    @Mock
    private PlayerRepositoryImpl playerRepository;
    @InjectMocks
    private PlayerServiceImpl playerService;


    @Test
    public void testFindAll(){
        //WHEN

        // LO QUE HACEMOS ACA ES DECIRLE QUE CUANDO(WHEN DE MOCKITO) SE LLAME AL METODO FINDALL
        //ESTE METODO NO SERA EL METODO REAL DE LA CLASE SERVICE O REPOSITORY SINO UNA SIMULACION
        //EN ESTE CASO RETORNARA LA LISTA DE JUGADORES QUE SE ENCUENTRA EN EL METODO PLAYERLISTMOCK DE LA CLASE DATAPROVIDER
        //ESTO SE HACE PARA TESTEAR EL COMPORTAMIENTO DEL METODO SIN QUE LOS CAMBIOS EN LOS DATOS A LOS QUE ACCEDE ESE METODO INFLUYAN EN LA PRUEBA
        when(playerRepository.findAll()).thenReturn(DataProvider.playerListMock());

        List<Player> result = playerService.findAll();

        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Lionel Messi",result.get(0).getName());
        assertEquals("Inter Miami",result.get(0).getTeam());
        assertEquals("Delantero",result.get(0).getPosition());
        verify(this.playerRepository).findAll(); //verifica que se llama al metodo de la dependencia



    }


    @Test
    public void testFindById(){
        //GIVEN
        Long id = 1L;
        //WHEN
        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.playerMock());

        Player player = this.playerService.findById(id);

        //THEN
        assertNotNull(player);
        assertEquals("Lionel Messi",player.getName());
        assertEquals("Inter Miami",player.getTeam());
        assertEquals("Delantero",player.getPosition());
        verify(this.playerRepository).findById(anyLong()); //verificamos si se esta llamando al metodo de la dependencia



    }

@Test
    public void testSavePlayer() {

        //GIVEN

    Player player = DataProvider.newPlayerMock();

       //WHEN

    this.playerService.savePlayer(player);

      //THEN
    verify(this.playerRepository).savePlayer(any(Player.class));//VERIFICA QUE SE LLAME AL METODO Y SE LE PASE COMO ARGUMENTO UN OBJETO DE TIPO PLAYER

    ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class); //CON ESTA CLASE PODEMOS CAPTURAR EL ARGUMENTO QUE SE CREO , O SEA EL PLAYER
    verify(this.playerRepository).savePlayer(playerArgumentCaptor.capture()); //AQUI VERIFICAMOS QUE SE LLAME AL METODO SAVE Y ADEMAS CAPTURAMOS EL OBJETO QUE SE ENVIO AL METODO
    assertEquals(7l, playerArgumentCaptor.getValue().getId());
    assertEquals("Lionel Campoy", playerArgumentCaptor.getValue().getName());



    }

    @Test
    public void testDeleteById() {
        //GIVEN
        Long id = 7L;

        //WHEN
        this.playerService.deleteById(id);

        //THEN
        verify(this.playerRepository).deleteById(anyLong());//VERIFICAMOS QUE SE LLAME AL METODO Y QUE EL PARAMETRO SEA DE TIPO LONG
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository).deleteById(longArgumentCaptor.capture());//VERIFICAMOS LA LLAMADA Y CAPTURAMOS EL ARGUMENTO
        assertEquals(7L, longArgumentCaptor.getValue());

    }




}
