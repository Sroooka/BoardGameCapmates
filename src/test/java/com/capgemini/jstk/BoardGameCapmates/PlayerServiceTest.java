package com.capgemini.jstk.BoardGameCapmates;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.*;

import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.*;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerMapDAO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@SuppressWarnings({ "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

	@InjectMocks
	PlayerService playerService;

	@Mock
	PlayerMapDAO playerMapDAO;

	@Before
	public void setUp() throws NonExistingPlayerException {
		Mockito.when(playerMapDAO.size()).thenReturn(500);
		
		Player player = new Player("Sroka");
		Mockito.when(playerMapDAO.getUserByNick(Mockito.anyString())).thenReturn(player);
	}

	@Test
	public void shouldReturnPlayersAmount() {
		// given
		// when
		playerService.getPlayersAmount();
		// then
		Mockito.verify(playerMapDAO).size();
	}

	@Test
	public void shouldAddPlayer() throws ExistingNicknameException {
		// given
		// when
		playerService.addPlayer("Sroka");
		// then
		Mockito.verify(playerMapDAO).addPlayer(Mockito.any());
	}
	
	@Test
	public void shouldReturnPlayerInfo() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		// when
		playerService.getPlayerInformations("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.anyString());
	}
	
	@Test
	public void shouldSearchPlayersByRank(){
		
	}
}
