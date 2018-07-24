package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;


public class Player {
	private String nickname;
	private int points;
	private Rank rank;
	private String playerDescription;
	private List<BoardGame> ownedGames;

	public Player() {
		this.nickname = null;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
	}
	
	public Player(String nickname) {
		this.nickname = nickname;
		this.points = 0;
		this.rank = Rank.NEWBIE;
		this.playerDescription = "";
		this.ownedGames = new ArrayList<BoardGame>();
	}
	

	public void addGame(BoardGame game) {
		if (!ownedGames.contains(game)) {
			ownedGames.add(game);
		}
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getPlayerDescription() {
		return playerDescription;
	}

	public void setPlayerDescription(String playerDescription) {
		this.playerDescription = playerDescription;
	}

	public List<BoardGame> getOwnedGames() {
		return ownedGames;
	}

	public void setOwnedGames(List<BoardGame> ownedGames) {
		this.ownedGames = ownedGames;
	}



	
}
