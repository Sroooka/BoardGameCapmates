package com.capgemini.jstk.BoardGameCapmates.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.model.Rank;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RestController
public class PlayerController {

	private PlayerService playerAccountManager;

	@Autowired
	public PlayerController(PlayerService playerAccountManager) {
		this.playerAccountManager = playerAccountManager;
	}

	public PlayerTO getPlayerInformations(String nickname) throws NonExistingPlayerException {
		return playerAccountManager.getPlayerInformations(nickname);

	}

	public Map<String, PlayerTO> searchPlayersByRank(Rank rank){
		return playerAccountManager.searchPlayersByRank(rank);
	}
	
	public Map<String, PlayerTO> searchPlayersByGame(BoardGame game){
		return playerAccountManager.searchPlayersByGame(game);
	}
}
