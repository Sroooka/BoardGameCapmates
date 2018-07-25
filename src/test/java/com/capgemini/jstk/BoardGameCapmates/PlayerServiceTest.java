package com.capgemini.jstk.BoardGameCapmates;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.Assert.*;
import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.model.Player;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerListDAO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

	@InjectMocks
	PlayerService playerService;

	@Mock
	PlayerListDAO playerListDao;
	
	@Test 
	public void shouldAddPlayer() throws ExistingNicknameException{
		//given
		//when
		playerService.addPlayer("Sroka");
		//then
		Mockito.verify(playerListDao).addPlayer(Mockito.any());
	}
	
	@Test
	public void shouldReturnValidAmountOfPlayers(){
		//given
		//when
		playerService.getPlayersAmount();
		//then
		Mockito.verify(playerListDao).size();
		
	}
}
