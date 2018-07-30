package com.capgemini.jstk.BoardGameCapmates;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.BoardGameCapmates.controller.PlayerController;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerControllerTest {

	@InjectMocks
	PlayerController playerController;
	
	@Mock 
	PlayerService playerService;
	
	
}
