package com.capgemini.jstk.BoardGameCapmates.model.TO;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.jstk.BoardGameCapmates.enums.Rank;
import com.capgemini.jstk.BoardGameCapmates.model.entity.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;

public class PlayerSearchTO {
	private Rank rank;
	private List<BoardGame> ownedGames;
	private AbilityTime abilityTime;

	public PlayerSearchTO() {
		this.ownedGames = new ArrayList<>();
	}

	public PlayerSearchTO(Rank rank, List<BoardGame> ownedGames, AbilityTime abilityTime) {
		super();
		this.rank = rank;
		this.ownedGames = ownedGames;
		this.abilityTime = abilityTime;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public List<BoardGame> getOwnedGames() {
		return ownedGames;
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
