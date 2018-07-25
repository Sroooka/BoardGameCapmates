package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.List;
import java.util.Random;

public class Game {
	private BoardGame boardGame;
	private int numberOfPlayers;
	private List<String> listOfPlayerNicknames;
	private List<String> points;
	
	
	
	public Game(BoardGame boardGame, int numberOfPlayers, List<String> listOfPlayerNicknames) {
		this.boardGame = boardGame;
		this.numberOfPlayers = numberOfPlayers;
		this.listOfPlayerNicknames = listOfPlayerNicknames;
	}

	//napisac w game service funkcje, ktora ustawi graczy w losowej kolejnosci
	//nastepnie przyzna im punkty i wywola na instancji player service funkcje dodajaca punkty
	//w zaleznosci od nazwy gracza
	//pamietaj zeby zaktualizowac range gracza po dodaniu punktow
}
