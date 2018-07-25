package com.capgemini.jstk.BoardGameCapmates.mapper;

import com.capgemini.jstk.BoardGameCapmates.model.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.Player;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;

public class GameMapper {
	public static Player makePlayerFromTO(PlayerTO newPlayerTO) {
		Player player = new Player();
		player.setNickname(newPlayerTO.getNickname());
		player.setOwnedGames(newPlayerTO.getOwnedGames());
		player.setPlayerDescription(newPlayerTO.getPlayerDescription());
		player.setPoints(0);
		player.setRank(newPlayerTO.getRank());
		player.setAbilityTime(new AbilityTime());
		return player;
	}
	
	public static PlayerTO makeTOFromPlayer(Player newPlayer) {
		PlayerTO player = new PlayerTO();
		player.setNickname(newPlayer.getNickname());
		player.setOwnedGames(newPlayer.getOwnedGames());
		player.setPlayerDescription(newPlayer.getPlayerDescription());
		player.setRank(newPlayer.getRank());
		player.setAbilityTime(newPlayer.getAbilityTime());
		return player;
	}
}
