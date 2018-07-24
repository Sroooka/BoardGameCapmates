package com.capgemini.jstk.BoardGameCapmates.mapper;

import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.model.Player;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;

@Service
public class PlayerMapper {

	public static Player makePlayerFromTO(PlayerTO newPlayerTO) {
		Player player = new Player();
		player.setNickname(newPlayerTO.getNickname());
		player.setOwnedGames(newPlayerTO.getOwnedGames());
		player.setPlayerDescription(newPlayerTO.getPlayerDescription());
		player.setPoints(0);
		player.setRank(newPlayerTO.getRank());
		return player;
	}
	
	public static PlayerTO makeTOFromPlayer(Player newPlayer) {
		PlayerTO player = new PlayerTO();
		player.setNickname(newPlayer.getNickname());
		player.setOwnedGames(newPlayer.getOwnedGames());
		player.setPlayerDescription(newPlayer.getPlayerDescription());
		player.setRank(newPlayer.getRank());
		return player;
	}
}
