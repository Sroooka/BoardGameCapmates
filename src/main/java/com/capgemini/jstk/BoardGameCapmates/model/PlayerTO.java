package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.List;

public class PlayerTO {
	private String nickname;
	private Rank rank;
	private String playerDescription;
	private List<BoardGame> ownedGames;
	private AbilityTime abilityTime;


	public PlayerTO() {
		this.nickname = null;
		this.rank = null;
		this.playerDescription = null;
		this.ownedGames = null;
		abilityTime = new AbilityTime();
	}
	
	public PlayerTO(String nickname, Rank rank, String playerDescription, List<BoardGame> ownedGames) {
		this.nickname = nickname;
		this.rank = rank;
		this.playerDescription = playerDescription;
		this.ownedGames = ownedGames;
		abilityTime = new AbilityTime();
	}

	public String getNickname() {
		return nickname;
	}

	public Rank getRank() {
		return rank;
	}

	public String getPlayerDescription() {
		return playerDescription;
	}

	public List<BoardGame> getOwnedGames() {
		return ownedGames;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public void setPlayerDescription(String playerDescription) {
		this.playerDescription = playerDescription;
	}

	public void setOwnedGames(List<BoardGame> ownedGames) {
		this.ownedGames = ownedGames;
	}
	
	public AbilityTime getAbilityTime() {
		return abilityTime;
	}

	public void setAbilityTime(AbilityTime abilityTime) {
		this.abilityTime = abilityTime;
	}

}
