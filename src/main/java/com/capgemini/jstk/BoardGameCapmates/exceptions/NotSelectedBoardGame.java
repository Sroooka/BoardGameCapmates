package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class NotSelectedBoardGame extends Exception {

	private static final long serialVersionUID = -1110078894850441933L;

	public NotSelectedBoardGame() {
		super("You must select board game!");
	}
	
	public NotSelectedBoardGame(String message) {
		super("You must select board game! " + message);
	}
}
