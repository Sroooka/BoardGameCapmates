package com.capgemini.jstk.BoardGameCapmates.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingChallengeException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.mapper.ChallengeMapper;
import com.capgemini.jstk.BoardGameCapmates.model.Challenge;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;

@Repository
public class ChallengeListDAO {
	private Map<Integer, Challenge> challengeList;
	private int gameID;

	public ChallengeListDAO() {
		this.challengeList = new HashMap<>();
		this.gameID = 0;
	}
	
	public void addChallenge(Challenge challenge){
		challengeList.put(gameID, challenge);
		gameID++;
	}

	public void addChallengeTO(ChallengeTO challenge) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		challengeList.put(gameID, ChallengeMapper.makeChallengeFromTO(challenge));
		gameID++;
	}

	public Integer getID(ChallengeTO challenge) throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeList.entrySet()) {
			if (entry.getValue().equals(challenge)) {
				return entry.getKey();
			}
		}

		throw new NonExistingChallengeException();
	}
}
