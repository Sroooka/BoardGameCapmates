package com.capgemini.jstk.BoardGameCapmates.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingChallengeException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.Challenge;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.repository.ChallengeListDAO;

@Service
public class ChallengeService {

	private final ChallengeListDAO challengeDAO;
	private final PlayerService playerService;

	@Autowired
	ChallengeService(ChallengeListDAO challengeDAO, PlayerService playerService) {
		System.out.println("Im in player service constructor");
		this.challengeDAO = challengeDAO;
		this.playerService = playerService;

	}
	
	public void createNewChallenge(String creatorNickname, BoardGame game, LocalTime gameStart, LocalTime gameEnd, String invitationMessage, String ... nicknames) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException, NonExistingChallengeException, NonExistingPlayerException {
		Challenge challenge = new Challenge();
		challenge.setGameStart(gameStart);
		challenge.setGameEnd(gameEnd);
		challenge.setGame(game);
		challenge.setInvitationMessage(invitationMessage);
		List<String> playerList = new ArrayList<>();
		for(int i=0; i<nicknames.length;i++){
			playerList.add(nicknames[i]);
		}
		challenge.setListOfPlayerNicknames(playerList);
		challenge.setOwnerNickname(creatorNickname);
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challengeDAO.addChallenge(challenge);
		playerService.invitePlayers(challenge);
	}
	
	public void acceptChallenge(String nickname, Challenge challenge, String comment) throws NonExistingChallengeException{
		challengeDAO.acceptChallenge(nickname, challenge, comment);
	}
	
	public void rejectChallenge(String nickname, Challenge challenge, String comment) throws NonExistingChallengeException{
		challengeDAO.rejectChallenge(nickname, challenge, comment);
	}
	
	public List<ChallengeTO> getChallengesThrownBySystem(String myNickname) throws NonExistingPlayerException{
		return playerService.getChallengesThrownBySystem(myNickname);
	}
	
	public List<ChallengeTO> getChallengesThrownByOtherUsers(String myNickname) throws NonExistingPlayerException{
		return playerService.getChallengesThrownByOtherUsers(myNickname);
	}
	
	public List<ChallengeTO> getChallengesThrownAtOtherUsers(String myNickname) throws NonExistingPlayerException{
		return playerService.getChallengesThrownAtOtherUsers(myNickname);
	}
	
	public List<ChallengeTO> getChallengesAcceptedByPlayer(String myNickname) throws NonExistingPlayerException{
		return playerService.getChallengesAcceptedByPlayer(myNickname);
	}
	
	public List<ChallengeTO> getAllOpponentChallenges(String opponentNickname) throws NonExistingPlayerException{
		return challengeDAO.getAllOpponentChallenges(opponentNickname);
	}
	
	public List<PlayerTO> checkOpponentInfo(Challenge challenge) throws NonExistingPlayerException{
		return playerService.checkOpponentInfo(challenge);
	}

}
