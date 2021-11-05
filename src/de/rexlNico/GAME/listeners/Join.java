package de.rexlNico.GAME.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.rexlNico.GAME.GameStates.GameStateHandler;
import de.rexlNico.GAME.GameStates.LobbyState;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class Join implements Listener{
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void on(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		
		for(Player a:Bukkit.getOnlinePlayers()){
			p.showPlayer(a);
			a.showPlayer(p);
		}
		p.sendMessage("§1➦ §9Lebenvoting§1");
		TextComponent message1 = new TextComponent("§1➡ §91§1» §73 Leben  §1│ §7Votes §6"+Var.livevote3.size());
		message1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 1"));
		TextComponent message2 = new TextComponent("§1➡ §91§2» §73 Leben  §1│ §7Votes §6"+Var.livevote5.size());
		message2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 2"));
		TextComponent message3 = new TextComponent("§1➡ §91§3» §73 Leben  §1│ §7Votes §6"+Var.livevote7.size());
		message3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 3"));
		TextComponent message4 = new TextComponent("§1➡ §91§4» §73 Leben  §1│ §7Votes §6"+Var.livevote10.size());
		message4.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 4"));
		p.spigot().sendMessage(message1);
		p.spigot().sendMessage(message2);
		p.spigot().sendMessage(message3);
		p.spigot().sendMessage(message4);
		p.sendMessage("§1➦ §9Lebenvoting§1");
		
		
		
		
		if(GameStateHandler.getGamestate() instanceof LobbyState){
			p.teleport(Factory.getConfigLocation("Lobby", Var.cfg));
			
			LobbyState ls = (LobbyState) GameStateHandler.getGamestate();
			Var.playing.add(p);
			
			Bukkit.broadcastMessage(Var.pr+"Der Spieler §a"+p.getDisplayName()+"§7 ist dem Spiel beigetreten. §r §8[§c"+Var.playing.size()+"§8/§c"+Var.MAX_PLAYERS+"§8]");
			p.setLevel(0);
			p.getInventory().clear();
			
			if(Var.playing.size() >= Var.MIN_PLAYERS){
				if(ls.getCountdown().isRunning == false){
					ls.getCountdown().stopIdle();
					ls.getCountdown().start();
				}
			}
			if(Var.playing.size() < Var.MIN_PLAYERS){
				if(ls.getCountdown().isIdelind == false){
					ls.getCountdown().idle();
				}
			}
			
			
		}
	}
	
}
