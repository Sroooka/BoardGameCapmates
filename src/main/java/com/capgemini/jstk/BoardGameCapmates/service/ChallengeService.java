package com.capgemini.jstk.BoardGameCapmates.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingChallengeException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.Challenge;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.repository.ChallengeListDAO;

@Service
public class ChallengeService {

	private final ChallengeListDAO challengeDAO;

	@Autowired
	ChallengeService(ChallengeListDAO challengeDAO) {
		System.out.println("Im in player service constructor");
		this.challengeDAO = challengeDAO;

	}
	
	public Integer createNewChallenge(String creatorNickname, BoardGame game, LocalTime gameStart, LocalTime gameEnd, String ... nicknames) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException, NonExistingChallengeException {
		ChallengeTO challenge = new ChallengeTO();
		challenge.setGameStart(gameStart);
		challenge.setGameEnd(gameEnd);
		challenge.setGame(game);
		List<String> playerList = new ArrayList<>();
		for(int i=0; i<nicknames.length;i++){
			playerList.add(nicknames[i]);
		}
		challenge.setListOfPlayerNicknames(playerList);
		challenge.setOwnerNickname(creatorNickname);
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challengeDAO.addChallengeTO(challenge);
		return challengeDAO.getID(challenge);
	}

}
