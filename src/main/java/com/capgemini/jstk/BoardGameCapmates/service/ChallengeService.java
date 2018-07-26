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
import com.capgemini.jstk.BoardGameCapmates.mapper.ChallengeMapper;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.repository.ChallengeMapDAO;

@Service
public class ChallengeService {

	private final ChallengeMapDAO challengeDAO;
	private final PlayerService playerService;

	@Autowired
	ChallengeService(ChallengeMapDAO challengeDAO, PlayerService playerService) {
		System.out.println("Im in player service constructor");
		this.challengeDAO = challengeDAO;
		this.playerService = playerService;

	}

	public ChallengeTO createNewChallenge(String creatorNickname, BoardGame game, LocalTime gameStart, LocalTime gameEnd,
			String invitationMessage, String... nicknames) throws NotSelectedBoardGame, TooMuchPlayersException,
			NotEnoughPlayersException, NonExistingChallengeException, NonExistingPlayerException {
		ChallengeTO challengeTO = new ChallengeTO();
		challengeTO.setGameStart(gameStart);
		challengeTO.setGameEnd(gameEnd);
		challengeTO.setGame(game);
		challengeTO.setInvitationMessage(invitationMessage);
		List<String> playerList = new ArrayList<>();
		for (int i = 0; i < nicknames.length; i++) {
			playerList.add(nicknames[i]);
		}
		challengeTO.setListOfPlayerNicknames(playerList);
		challengeTO.setOwnerNickname(creatorNickname);
		challengeTO.setChallengeCreator(ChallengeCreator.PLAYER);
		challengeDAO.addChallenge(challengeTO);
		playerService.invitePlayers(challengeTO);
		return challengeTO;
	}

	public void acceptChallenge(String nickname, ChallengeTO challengeTO, String comment)
			throws NonExistingChallengeException, NotSelectedBoardGame, TooMuchPlayersException,
			NotEnoughPlayersException {
		challengeDAO.acceptChallenge(nickname, ChallengeMapper.makeChallengeFromTO(challengeTO), comment);
	}

	public void rejectChallenge(String nickname, ChallengeTO challengeTO, String comment)
			throws NonExistingChallengeException, NotSelectedBoardGame, TooMuchPlayersException,
			NotEnoughPlayersException {
		challengeDAO.rejectChallenge(nickname, ChallengeMapper.makeChallengeFromTO(challengeTO), comment);
	}

	public List<ChallengeTO> getChallengesThrownBySystem(String myNickname) throws NonExistingPlayerException {
		return playerService.getChallengesThrownBySystem(myNickname);
	}

	public List<ChallengeTO> getChallengesThrownByOtherUsers(String myNickname) throws NonExistingPlayerException {
		return playerService.getChallengesThrownByOtherUsers(myNickname);
	}

	public List<ChallengeTO> getChallengesThrownAtOtherUsers(String myNickname) throws NonExistingPlayerException {
		return playerService.getChallengesThrownAtOtherUsers(myNickname);
	}

	public List<ChallengeTO> getChallengesAcceptedByPlayer(String myNickname) throws NonExistingPlayerException {
		return playerService.getChallengesAcceptedByPlayer(myNickname);
	}

	public List<ChallengeTO> getAllOpponentChallenges(String opponentNickname) throws NonExistingPlayerException {
		return challengeDAO.getAllOpponentChallenges(opponentNickname);
	}

	public List<PlayerTO> checkOpponentInfo(ChallengeTO challengeTO) throws NonExistingPlayerException {
		return playerService.checkOpponentInfo(challengeTO);
	}

	public void StartGame(ChallengeTO challengeTO)
			throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		List<String> winners = challengeDAO.StartGame(challengeTO);
		int score = winners.size();
		for (int i = 0; i < winners.size(); i++) {
			playerService.addScore(winners.get(i), score);
			playerService.addGameWhichTookPlace(winners.get(i), challengeDAO.getGameByChallengeTO(challengeTO));
			score--;
		}
	}

	public void commentGameWhichTookPlace(String nickname, ChallengeTO challengeTO, String comment) {
		challengeDAO.commentGameWhichTookPlace(nickname, challengeTO, comment);
	}

	public int getChallengesAmount() {
		return (int) challengeDAO.size();
	}
}
