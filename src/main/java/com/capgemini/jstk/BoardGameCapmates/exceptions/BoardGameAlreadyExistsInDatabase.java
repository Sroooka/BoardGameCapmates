package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class BoardGameAlreadyExistsInDatabase extends Exception {

	private static final long serialVersionUID = -6474382027127103734L;

	public BoardGameAlreadyExistsInDatabase() {
		super("There are game with this name in database!");
	}
	
	public BoardGameAlreadyExistsInDatabase(String message) {
		super("There are game with this name in database! " + message);
	}
}
