package com.capgemini.jstk.BoardGameCapmates;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import org.springframework.test.context.junit4.SpringRunner;
import com.capgemini.jstk.BoardGameCapmates.enums.Rank;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerSearchTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    public void shouldShowPlayerInformationsByNickname() throws Exception {
        //given
        List<PlayerTO> players = new ArrayList<>();
        PlayerTO player = new PlayerTO();
        player.setNickname("sroka1");
        player.setPlayerDescription("opis");
        player.setRank(Rank.GOD_OF_BOARD_GAMES);
        players.add(player);
        players.add(player);
        players.add(player);

        //when
        Mockito.when(playerService.getPlayerInformations("sroka1")).thenReturn(player);
        mockMvc.perform(get("/player/sroka1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("nickname",is("sroka1")))
                .andExpect(jsonPath("rank",is("GOD_OF_BOARD_GAMES")))
                .andExpect(jsonPath("playerDescription",is("opis")));
        //then
        verify(playerService, times(1)).getPlayerInformations("sroka1");
    }
    
    @Test
    public void shouldReturnError404WhenNoPlayerWithThisNickname() throws Exception {
        //given
        //when
        Mockito.when(playerService.getPlayerInformations(Mockito.anyString())).thenReturn(null);
        mockMvc.perform(get("/player/sroka"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        //then
    }
    
    @Test
    public void shouldUpdatePlayer() throws Exception {
        //given
        PlayerTO player = new PlayerTO();
        player.setNickname("sroka");
        player.setPlayerDescription("opis");
        player.setRank(Rank.GOD_OF_BOARD_GAMES);
        //when
        Mockito.when(playerService.updatePlayer(Mockito.any())).thenReturn(player);
        mockMvc.perform(post("/player/update-player").flashAttr("update", player))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        //then
        verify(playerService, times(1)).updatePlayer(Mockito.any());
    }
    
    @Test
    public void shouldSearchPlayerByCriteria() throws Exception {
        //given
    	PlayerSearchTO criteria = new PlayerSearchTO();
        criteria.setRank(Rank.GOD_OF_BOARD_GAMES);
        List<PlayerSearchTO> players = new ArrayList<>();
        PlayerSearchTO player = new PlayerSearchTO();
        player.setRank(Rank.GOD_OF_BOARD_GAMES);
        players.add(player);
        players.add(player);
        players.add(player);
        //when
        Mockito.when(playerService.searchPlayersByCriteria(criteria)).thenReturn(players);
        mockMvc.perform(get("/player/search-player").flashAttr("search", criteria))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        //then
        verify(playerService, times(1)).searchPlayersByCriteria(Mockito.any());
    }
}
