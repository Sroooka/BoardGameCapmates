package com.capgemini.jstk.BoardGameCapmates.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.Challenge;
import com.capgemini.jstk.BoardGameCapmates.model.ChallengeTO;

@Component
public class ChallengeMapper {
	public static Challenge makeChallengeFromTO(ChallengeTO newChallengeTO) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		Challenge challenge = new Challenge();
		challenge.setChallengeCreator(newChallengeTO.getChallengeCreator());
		challenge.setChallengeID(newChallengeTO.getChallengeID());
		challenge.setOwnerNickname(newChallengeTO.getOwnerNickname());
		challenge.setGame(newChallengeTO.getGame());
		challenge.setGameStart(newChallengeTO.getGameStart());
		challenge.setGameEnd(newChallengeTO.getGameEnd());
		challenge.setListOfPlayerNicknames(newChallengeTO.getListOfPlayerNicknames());
		challenge.setInvitationMessage(newChallengeTO.getInvitationMessage());
		challenge.setNumberOfPlayers(newChallengeTO.getNumberOfPlayers());

		return challenge;
	}

	public static ChallengeTO makeTOFromChallenge(Challenge newChallenge) {
		ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeCreator(newChallenge.getChallengeCreator());
		challenge.setChallengeID(newChallenge.getChallengeID());
		challenge.setOwnerNickname(newChallenge.getOwnerNickname());
		challenge.setGame(newChallenge.getGame());
		challenge.setGameEnd(newChallenge.getGameEnd());
		challenge.setGameStart(newChallenge.getGameStart());
		challenge.setListOfPlayerNicknames(newChallenge.getListOfPlayerNicknames());
		challenge.setNumberOfPlayers(newChallenge.getNumberOfPlayers());
		challenge.setInvitationMessage(newChallenge.getInvitationMessage());
		return challenge;
	}
}
