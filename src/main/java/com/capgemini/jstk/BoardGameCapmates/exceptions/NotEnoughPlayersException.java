package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class NotEnoughPlayersException extends Exception {

	private static final long serialVersionUID = -6810855947022844939L;
	
	public NotEnoughPlayersException() {
		super("Not enough players for this game!");
	}
	
	public NotEnoughPlayersException(String message) {
		super("Not enough players for this game! " + message);
	}
}
