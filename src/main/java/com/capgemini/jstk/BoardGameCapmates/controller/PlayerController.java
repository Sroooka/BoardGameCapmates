package com.capgemini.jstk.BoardGameCapmates.controller;

import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingChallengeException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.model.Rank;
import com.capgemini.jstk.BoardGameCapmates.service.ChallengeService;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RestController
public class PlayerController {

	private PlayerService playerAccountManager;
	private ChallengeService challengeManager;

	@Autowired
	public PlayerController(PlayerService playerAccountManager, ChallengeService challengeManager) {
		this.playerAccountManager = playerAccountManager;
		this.challengeManager = challengeManager;
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
	
	public void setPlayerDescription(String nickname, String description) throws NonExistingPlayerException{
		playerAccountManager.setPlayerDescription(nickname, description);
	}
	
	public Map<String, PlayerTO> searchPlayersByAbilityTime(AbilityTime abilityTime) {
		return playerAccountManager.searchPlayersByAbilityTime(abilityTime);
	}
	
	public void createNewChallenge(String ownerNickname, BoardGame game, LocalTime gameStart, LocalTime gameEnd,
			String... nicknames) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException,
			NonExistingChallengeException, NonExistingPlayerException{
		Integer challengeID = challengeManager.createNewChallenge(ownerNickname, game, gameStart, gameEnd, nicknames);
		playerAccountManager.sendInvitations(challengeID, nicknames);
	}
	
	public void acceptInvitation(String nickname, Integer challengeID) throws NonExistingPlayerException{
		playerAccountManager.acceptInvitation(nickname, challengeID);
	}
	
	public void rejectInvitation(String nickname, Integer challengeID) throws NonExistingPlayerException{
		playerAccountManager.rejectInvitation(nickname, challengeID);
	}
}
