package com.capgemini.jstk.BoardGameCapmates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerSearchTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

import static org.springframework.http.MediaType.*;

import java.util.List;

@RestController
public class PlayerController {
	private PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/player/{nickname}", method = RequestMethod.GET)
	public PlayerTO getPlayerByNickname(@PathVariable(value = "nickname") String nickname) throws NonExistingPlayerException {
		PlayerTO playerTO = playerService.getPlayerInformations(nickname);
		if(playerTO != null){
            return  playerTO;
        }
        throw new NonExistingPlayerException();
	}

	@RequestMapping(value = "/player/update-player", method = RequestMethod.POST)
	public PlayerTO updatePlayerByNickname(@ModelAttribute("update") PlayerTO updatedPlayer)
			throws NonExistingPlayerException {
		playerService.updatePlayer(updatedPlayer);
		return updatedPlayer;
	}

	@RequestMapping(value = "/player/search-player", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public List<PlayerSearchTO> searchPlayer(@ModelAttribute("search") PlayerSearchTO searchedPlayer)
			throws NonExistingPlayerException {
		List<PlayerSearchTO> foundPlayers;
		foundPlayers = playerService.searchPlayersByCriteria(searchedPlayer);
		if(foundPlayers.isEmpty()){
			throw new NonExistingPlayerException();
		}
		return foundPlayers;
	}
}
