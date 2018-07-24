package com.capgemini.jstk.BoardGameCapmates;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.BoardGameCapmates.controller.PlayerController;
import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerControllerTest {

	@Mock
	PlayerService playerService;
	
	@InjectMocks
	PlayerController playerController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(playerService.getPlayersAmount()).thenReturn(5);
	}

	@Test
	public void addPlayerTest() throws ExistingNicknameException {
		// given
		// when
		ResponseEntity<Boolean> response = playerController.addNewPlayer("Sroka", "haslo", "jestem graczem");
		// then
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	//@Test
	public void addExistingPlayerTest() throws ExistingNicknameException {
		// given
		// when
		playerController.addNewPlayer("Sroka", "haslo", "jestem graczem");
		playerController.addNewPlayer("Sroka", "haslo", "jestem graczem");
		// then
		
	}

	//@Test
	public void shouldReturnCorrectSize() throws ExistingNicknameException {
		// given
		// when
		ResponseEntity<Integer> response = playerController.getPlayersAmount();
		//then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(new Integer(5), response.getBody());
	}
}
