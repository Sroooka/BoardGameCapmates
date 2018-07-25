package com.capgemini.jstk.BoardGameCapmates.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;

public class Challenge {
	private BoardGame game;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private LocalTime gameStart;
	private LocalTime gameEnd;
	private List<Boolean> acceptations;
	private List<String> gameResult;
	private ChallengeCreator challengeCreator;
	private String ownerNickname;

	public Challenge() {
		this.game = null;
		this.gameStart = null;
		this.gameEnd = null;
		this.ownerNickname = null;
		this.challengeCreator = null;
		this.numberOfPlayers = 0;
		this.listOfPlayerNicknames = new ArrayList<String>();
		this.gameResult = new ArrayList<String>();
		this.acceptations = new ArrayList<Boolean>();
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



	public void setListOfPlayerNicknames(List<String> listOfPlayerNicknames) throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		if(this.game == null){
			throw new NotSelectedBoardGame();
		}
		if(listOfPlayerNicknames.size() > this.game.getMaxPlayers()){
			throw new TooMuchPlayersException();
		}
		if(listOfPlayerNicknames.size() < this.game.getMinPlayers()){
			throw new NotEnoughPlayersException();
		}
		this.listOfPlayerNicknames = listOfPlayerNicknames;
		this.numberOfPlayers = listOfPlayerNicknames.size();
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



	public List<Boolean> getAcceptations() {
		return acceptations;
	}



	public void setAcceptations(List<Boolean> acceptations) {
		this.acceptations = acceptations;
	}



	public List<String> getGameResult() {
		return gameResult;
	}



	public void setGameResult(List<String> gameResult) {
		this.gameResult = gameResult;
	}



	public boolean canStart() {
		for (int i = 0; i < acceptations.size(); i++) {
			if (!acceptations.get(i)) {
				return false;
			}
		}
		return true;
	}
}