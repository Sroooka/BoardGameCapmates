package com.capgemini.jstk.BoardGameCapmates.model.TO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.jstk.BoardGameCapmates.enums.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;

public class ChallengeTO {
	private int challengeID;
	private BoardGame game;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private LocalTime gameStart;
	private LocalTime gameEnd;
	private List<String> gameResult;
	private ChallengeCreator challengeCreator;
	private String ownerNickname;
	private String invitationMessage;

	public ChallengeTO() {
		this.numberOfPlayers = 0;
		this.listOfPlayerNicknames = new ArrayList<String>();
		this.gameResult = new ArrayList<String>();
		this.invitationMessage = "";
	}
	
	public ChallengeTO(BoardGame game,int numberOfPlayers) {
		this.numberOfPlayers = 0;
		this.listOfPlayerNicknames = new ArrayList<String>();
		this.gameResult = new ArrayList<String>();
		this.invitationMessage = "";
	}
	
	
	public int getChallengeID() {
		return challengeID;
	}

	public void setChallengeID(int challengeID) {
		this.challengeID = challengeID;
	}

	public String getInvitationMessage() {
		return invitationMessage;
	}

	public void setInvitationMessage(String invitationMessage) {
		this.invitationMessage = invitationMessage;
	}

	public String getOwnerNickname() {
		return ownerNickname;
	}

	public void setOwnerNickname(String ownerNickname) {
		this.ownerNickname = ownerNickname;
	}

	public ChallengeCreator getChallengeCreator() {
		return challengeCreator;
	}

	public void setChallengeCreator(ChallengeCreator challengeCreator) {
		this.challengeCreator = challengeCreator;
	}

	public BoardGame getGame() {
		return game;
	}

	public void setGame(BoardGame game) {
		this.game = game;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public List<String> getListOfPlayerNicknames() {
		return listOfPlayerNicknames;
	}

	public void setListOfPlayerNicknames(List<String> listOfPlayerNicknames) {
		this.listOfPlayerNicknames = listOfPlayerNicknames;
	}

	public LocalTime getGameStart() {
		return gameStart;
	}

	public void setGameStart(LocalTime gameStart) {
		this.gameStart = gameStart;
	}

	public LocalTime getGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(LocalTime gameEnd) {
		this.gameEnd = gameEnd;
	}

	public List<String> getGameResult() {
		return gameResult;
	}

	public void setGameResult(List<String> gameResult) {
		this.gameResult = gameResult;
	}
}
