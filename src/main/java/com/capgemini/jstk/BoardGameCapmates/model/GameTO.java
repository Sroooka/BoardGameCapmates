package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.ArrayList;
import java.util.List;

public class GameTO {
	private BoardGame boardGame;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	
	public GameTO() {
		listOfPlayerNicknames = new ArrayList<>();
	}
	
	public GameTO(BoardGame boardGame, int numberOfPlayers, List<String> listOfPlayerNicknames) {
		this.boardGame = boardGame;
		this.numberOfPlayers = numberOfPlayers;
		this.listOfPlayerNicknames = listOfPlayerNicknames;
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
	



}
