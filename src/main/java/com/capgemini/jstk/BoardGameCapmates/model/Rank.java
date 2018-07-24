package com.capgemini.jstk.BoardGameCapmates.model;

public enum Rank {
	NEWBIE(0),
	WEAKLING(10),
	BEGINNER(20),
	EXPERIENCED_BEGINNER(40),
	MIDDLEBROW(80),
	EXPERIENCED_MIDDLEBORW(160),
	ADVANCED(320),
	PROFESSIONAL(640),
	MASTER(1280),
	ULTRA_MASTER(2560),
	ULTRA_TURBO_MASTER(5120),
	GOD_OF_BOARD_GAMES(99999);
	
	private final int pointsRequired;
	
	Rank(int points){
		this.pointsRequired = points;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}
}
