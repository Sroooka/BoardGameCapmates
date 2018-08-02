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

import com.capgemini.jstk.BoardGameCapmates.exceptions.ErrorMessage;
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
	public ResponseEntity<PlayerTO> getPlayerByNickname(@PathVariable(value = "nickname") String nickname) throws NonExistingPlayerException {
		PlayerTO playerTO = playerService.getPlayerInformations(nickname);
		return new ResponseEntity<>(playerTO, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/update-player", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerTO> updatePlayerByNickname(@RequestBody PlayerTO updatedPlayer)
			throws NonExistingPlayerException {
		playerService.updatePlayer(updatedPlayer);
		return new ResponseEntity<>(updatedPlayer, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/search-player", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PlayerSearchTO>> searchPlayer(@RequestBody PlayerSearchTO searchedPlayer)
			throws NonExistingPlayerException {
		List<PlayerSearchTO> foundPlayers;
		foundPlayers = playerService.searchPlayersByCriteria(searchedPlayer);
		return new ResponseEntity<>(foundPlayers, HttpStatus.FOUND);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage playerExceptionHandler(Exception ex) {
		LOGGER.error("Error in player service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(NonExistingPlayerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage playerNotFoundException(Exception ex) {
		LOGGER.error("Error in player service: ", ex);
		return new ErrorMessage(ex.getMessage());
	}

}
