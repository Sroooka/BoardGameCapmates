package com.capgemini.jstk.BoardGameCapmates.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private Map<Integer, Challenge> challengeMap;
	private int gameID;

	public ChallengeListDAO() {
		this.challengeMap = new HashMap<>();
		this.gameID = 0;
	}

	public void addChallenge(Challenge challenge) {
		challengeMap.put(gameID, challenge);
		gameID++;
	}

	public void addChallengeTO(ChallengeTO challenge)
			throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		challengeMap.put(gameID, ChallengeMapper.makeChallengeFromTO(challenge));
		gameID++;
	}

	public Integer getID(ChallengeTO challenge) throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getValue().equals(challenge)) {
				return entry.getKey();
			}
		}

		throw new NonExistingChallengeException();
	}

	public ChallengeTO getChallengeByID(Integer challengeID) {
		return ChallengeMapper.makeTOFromChallenge(challengeMap.get(challengeID));
	}

	public void acceptChallenge(String nickname, Challenge challenge, String comment)
			throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getValue().equals(challenge)) {
				entry.getValue().addAcceptation(nickname, comment);
			}
		}

		throw new NonExistingChallengeException();
	}

	public void rejectChallenge(String nickname, Challenge challenge, String comment)
			throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getValue().equals(challenge)) {
				entry.getValue().rejectAcceptation(nickname, comment);
			}
		}

		throw new NonExistingChallengeException();
	}

	public List<ChallengeTO> getAllOpponentChallenges(String opponentNickname) {
		List<ChallengeTO> returnList = new ArrayList<>();
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getValue().equals(opponentNickname)) {
				returnList.add(ChallengeMapper.makeTOFromChallenge(entry.getValue()));
			}
		}
		return returnList;
	}

}
