package com.capgemini.jstk.BoardGameCapmates.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	private BoardGame boardGame;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private List<String> playerComments;

	public Game() {
		listOfPlayerNicknames = new ArrayList<>();
		playerComments = new ArrayList<>();
	}

	public Game(BoardGame boardGame, int numberOfPlayers, List<String> listOfPlayerNicknames) {
		this.boardGame = boardGame;
		this.numberOfPlayers = numberOfPlayers;
		this.listOfPlayerNicknames = listOfPlayerNicknames;
		playerComments = new ArrayList<>();
	}

	public void startGame() {
		Collections.shuffle(listOfPlayerNicknames);
	}

	public void addPlayerComment(String nickname, String comment) {
		playerComments.add(nickname + ": " + comment);
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
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

	public List<String> getPlayerComments() {
		return playerComments;
	}

	public void setPlayerComments(List<String> playerComments) {
		this.playerComments = playerComments;
	}
}
