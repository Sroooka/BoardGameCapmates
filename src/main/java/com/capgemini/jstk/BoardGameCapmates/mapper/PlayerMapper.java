package com.capgemini.jstk.BoardGameCapmates.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerSearchTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.entity.Player;

@Component
public class PlayerMapper {

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
	
	public static PlayerSearchTO makeSearchedPlayerFromPlayer(Player player) {
		PlayerSearchTO playerSearchTO = new PlayerSearchTO();
		playerSearchTO.setAbilityTime(player.getAbilityTime());
		playerSearchTO.setOwnedGames(player.getOwnedGames());
		playerSearchTO.setRank(player.getRank());
		return playerSearchTO;
	}
}
