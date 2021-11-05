package de.rexlNico.GAME.Methodes;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.rexlNico.GAME.Main.Main;
import de.rexlNico.GAME.listeners.Move;

public class Death {

	public static void kill(Player p){
		removeLives(p, 1);
		for(int i = 0; i<Move.glassLocs.size(); i++){
			if(Move.blockP.get(Move.glassLocs.get(i)) == p){
				Move.glassLocs.get(i).getBlock().setType(Material.AIR);
				Move.blockP.remove(Move.glassLocs.get(i));
				Move.glassLocs.remove(i);
			}
		}
		for(Player a:Var.playing){
			int i = 1;
			ScoreboardAPI scoreboard = new ScoreboardAPI(a, "§8» §9Teqless §8● §fModie1");
			scoreboard.addLine("§r");
			scoreboard.addLine("§4Leben");
			for(Player all:Var.playing){
				if(i==1){
					scoreboard.addLine("§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==2){
					scoreboard.addLine("§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==3){
					scoreboard.addLine("§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==4){
					scoreboard.addLine("§r§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==5){
					scoreboard.addLine("§r§r§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==6){
					scoreboard.addLine("§r§r§r§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==7){
					scoreboard.addLine("§r§r§r§r§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}else if(i==8){
					scoreboard.addLine("§r§r§r§r§r§r§r§r§r");
					scoreboard.addLine("§a"+all.getName()+" §7: §6"+Var.lives.get(all));
				}
				i++;
			}
			scoreboard.sendScoreboard();
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				Move.protection.remove(p);
				
			}
		}, 20*10);
	}
	
	public static void raus(Player p){
		
		p.teleport(Factory.getConfigLocation("Spectator", Var.cfg));
		p.sendMessage(Var.pr+"Du hast nun keine Leben mehr und bist damit aus dem Spiel!");
		Var.spectating.add(p);
		for(Player a:Bukkit.getOnlinePlayers()){
			a.hidePlayer(p);
		}
		p.setAllowFlight(true);
		Var.playing.remove(p);
	}
	
	public static Integer getLives(Player p) {
		if (Var.lives.containsKey(p)) {
			return (Integer) Var.lives.get(p);
		}
		return Integer.valueOf(0);
	}

	public static void removeLives(Player p, Integer anzahl) {
		Integer i = 0;
		if (Var.lives.containsKey(p)) {
			i = Var.lives.get(p);
		}
		Var.lives.remove(p);
		Var.lives.put(p, i-1);
		if(Var.lives.get(p) == 0){
			raus(p);
			return;
		}
		int it = new Random().nextInt(8);
		while(it == 0){
			it = new Random().nextInt(8);
		}
		p.teleport(Factory.getConfigLocation("Spawn."+it, Var.cfg));
	}
	
}
