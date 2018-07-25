package com.capgemini.jstk.BoardGameCapmates.exceptions;

public class NonExistingChallengeException extends Exception {

	private static final long serialVersionUID = -3092169588364268528L;


	public NonExistingChallengeException() {
		super("There are challenge like expected!");
	}
	
	public NonExistingChallengeException(String message) {
		super("There are challenge like expected! " + message);
	}
}
