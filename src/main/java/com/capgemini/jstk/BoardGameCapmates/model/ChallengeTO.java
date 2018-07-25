package com.capgemini.jstk.BoardGameCapmates.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ChallengeTO {
	private BoardGame game;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private LocalTime gameStart;
	private LocalTime gameEnd;
	private List<String> gameResult;
	private ChallengeCreator challengeCreator;
	private String ownerNickname;

	public ChallengeTO() {
		this.game = null;
		this.gameStart = null;
		this.gameEnd = null;
		this.ownerNickname = null;
		this.challengeCreator = null;
		this.numberOfPlayers = 0;
		this.listOfPlayerNicknames = new ArrayList<String>();
		this.gameResult = new ArrayList<String>();
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
