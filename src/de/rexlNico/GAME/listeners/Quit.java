package de.rexlNico.GAME.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.rexlNico.GAME.GameStates.GameStateHandler;
import de.rexlNico.GAME.GameStates.LobbyState;
import de.rexlNico.GAME.Methodes.Var;

public class Quit implements Listener{

	@SuppressWarnings("static-access")
	@EventHandler
	public void on(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		if(GameStateHandler.getGamestate() instanceof LobbyState){
			LobbyState ls = (LobbyState) GameStateHandler.getGamestate();
			Var.playing.remove(p);
			
			
			Bukkit.broadcastMessage(Var.pr+"Der Spieler ?a"+p.getDisplayName()+"?7 hat das Spiel verlassen. ?r ?8[?c"+Var.playing.size()+"?8/?c"+Var.MAX_PLAYERS+"?8]");
			
			if(Var.playing.size() < Var.MAX_PLAYERS){
				if(ls.getCountdown().isRunning){
					ls.getCountdown().stopCountDown();
					ls.getCountdown().idle();
				}
			}
		}else{
			
			if(Var.playing.contains(p)) Var.playing.remove(p);
		}
	}
	
}
