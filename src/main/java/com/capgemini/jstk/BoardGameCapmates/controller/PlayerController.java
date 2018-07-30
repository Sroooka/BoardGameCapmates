package com.capgemini.jstk.BoardGameCapmates.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerSearchTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

import static org.springframework.http.MediaType.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

	private PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;

	}

	@RequestMapping(value = "/{nickname}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerTO> getPlayerByNickname(@PathVariable(value = "nickname") String nickname) {

		try {
			PlayerTO playerTO = playerService.getPlayerInformations(nickname);
			return new ResponseEntity<>(playerTO, HttpStatus.FOUND);
		} catch (NonExistingPlayerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/update-player", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerTO> updatePlayerByNickname(@RequestBody PlayerTO updatedPlayer) {

		try {
			playerService.updatePlayer(updatedPlayer);
			return new ResponseEntity<>(updatedPlayer, HttpStatus.FOUND);
		} catch (NonExistingPlayerException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/search-player", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlayerSearchTO>> searchPlayer(@RequestBody PlayerSearchTO searchedPlayer) {
		List<PlayerSearchTO> foundPlayers;
		try {
			foundPlayers = playerService.searchPlayersByCriteria(searchedPlayer);
			return new ResponseEntity<>(foundPlayers, HttpStatus.FOUND);
		} catch (NonExistingPlayerException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error playerExceptionHandler(Exception ex) {
		LOGGER.error("Error in player service: ", ex);
		return new Error(ex.getMessage());
	}

}
