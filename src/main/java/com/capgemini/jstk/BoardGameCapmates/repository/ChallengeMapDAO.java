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
import com.capgemini.jstk.BoardGameCapmates.mapper.GameMapper;
import com.capgemini.jstk.BoardGameCapmates.model.Challenge;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.GameTO;

@Repository
public class ChallengeMapDAO {
	private Map<Integer, Challenge> challengeMap;
	private int gameID;

	public ChallengeMapDAO() {
		this.challengeMap = new HashMap<>();
		this.gameID = 0;
	}

	public void addChallenge(ChallengeTO challengeTO) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		Challenge challenge = ChallengeMapper.makeChallengeFromTO(challengeTO);
		challengeMap.put(gameID, challenge);
		challengeMap.get(gameID).setChallengeID(gameID);
		gameID++;
	}

	public void addChallengeTO(ChallengeTO challenge)
			throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		challengeMap.put(gameID, ChallengeMapper.makeChallengeFromTO(challenge));
		challengeMap.get(gameID).setChallengeID(gameID);
		gameID++;
	}

	
	public ChallengeTO getChallengeByID(Integer challengeID) {
		return ChallengeMapper.makeTOFromChallenge(challengeMap.get(challengeID));
	}

	public void acceptChallenge(String nickname, Challenge challenge, String comment)
			throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getKey()==challenge.getChallengeID()) {
				entry.getValue().addAcceptation(nickname, comment);
			}
		}
		throw new NonExistingChallengeException();
	}

	public void rejectChallenge(String nickname, Challenge challenge, String comment)
			throws NonExistingChallengeException {
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getKey()==challenge.getChallengeID()) {
				entry.getValue().rejectAcceptation(nickname, comment);
			}
		}
		throw new NonExistingChallengeException();
	}

	public List<ChallengeTO> getAllOpponentChallenges(String opponentNickname) {
		List<ChallengeTO> returnList = new ArrayList<>();
		for (Map.Entry<Integer, Challenge> entry : challengeMap.entrySet()) {
			if (entry.getValue().getOwnerNickname().equals(opponentNickname)) {
				returnList.add(ChallengeMapper.makeTOFromChallenge(entry.getValue()));
			}
		}
		return returnList;
	}

	public List<String> StartGame(ChallengeTO challengeTO) {
		Challenge currentChallenge = challengeMap.get(challengeTO.getChallengeID());
		currentChallenge.getTheGameTookPlace().startGame();
		return currentChallenge.getTheGameTookPlace().getListOfPlayerNicknames();
		
	}

	public void commentGameWhichTookPlace(String nickname, ChallengeTO challengeTO, String comment) {
		Challenge currentChallenge = challengeMap.get(challengeTO.getChallengeID());
		currentChallenge.getTheGameTookPlace().addPlayerComment(nickname, comment);
	}
	
	public GameTO getGameByChallengeTO(ChallengeTO challenge){
		return GameMapper.makeTOFromGame(challengeMap.get(challenge.getChallengeID()).getTheGameTookPlace());
	}

	public Object size() {
		return this.challengeMap.size();
	}

}
