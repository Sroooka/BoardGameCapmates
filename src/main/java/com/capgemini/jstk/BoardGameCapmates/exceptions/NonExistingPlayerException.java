package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class NonExistingPlayerException extends Exception {

	private static final long serialVersionUID = -7103888730660570223L;

	public NonExistingPlayerException() {
		super("There are player with this nickname!");
	}
	
	public NonExistingPlayerException(String message) {
		super("There are player with this nickname! " + message);
	}
}
