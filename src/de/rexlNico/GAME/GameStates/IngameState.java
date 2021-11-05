package de.rexlNico.GAME.GameStates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.rexlNico.GAME.Main.Main;
import de.rexlNico.GAME.Methodes.Death;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.ScoreboardAPI;
import de.rexlNico.GAME.Methodes.Var;
import de.rexlNico.GAME.SpawnerPack.Spawner;
import de.rexlNico.GAME.listeners.Move;

public class IngameState extends GameState{
	
	private int TaskID, TaskID2;
	
	@Override
	public void init() {
		int lv = Var.livevote3.size();
		Var.LIVES = 3;
		if(lv < Var.livevote5.size()){
			lv = Var.livevote5.size();
			Var.LIVES = 5;
		}
		if(lv < Var.livevote7.size()){
			lv = Var.livevote7.size();
			Var.LIVES = 7;
		}
		if(lv < Var.livevote10.size()){
			lv = Var.livevote10.size();
			Var.LIVES = 10;
		}
		
		for(Player a:Bukkit.getOnlinePlayers()){
			Var.lives.put(a, Integer.valueOf(Var.LIVES));
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
		
		for(int i = 0; i<Var.playing.size(); i++){
			Var.playing.get(i).teleport(Factory.getConfigLocation("Spawn."+(i+1), Var.cfg));
			Var.playing.get(i).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, -127));
		}
		Bukkit.broadcastMessage("§4Versuche die Leben der Spieler auf null zu bekommen, in dem sie in deine farbige Spur laufen.");
		Move.move = true;
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				new Spawner();
				
			}
		}, 0, 20*15);
		
		//Testen ob das Ende ist
		TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				if(Var.playing.size() == 1){
					Move.move = false;
					for(Player a:Bukkit.getOnlinePlayers()){
						a.sendMessage(Var.pr+"§4"+Var.playing.get(0).getName()+" §7hat §aGewonnen");
						a.sendTitle("§aGewonnen", Var.playing.get(0).getName());
					}
					Bukkit.getScheduler().cancelTask(TaskID);
					Bukkit.broadcastMessage(Var.pr+"§4Der Server startet in 15 Sekunden neu!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							Bukkit.broadcastMessage(Var.pr+"§4Der Server startet jetzt neu!");
							GameStateHandler.setGameState(GameState.END_STATE);
						}
					}, 20*15);
					
				}
				
				
			}
		}, 0, 20*1);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				
				for(Player a:Var.playing){
					Move.lastloc.put(a, a.getLocation());
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						for(Player a:Var.playing){
							if(Move.lastloc.get(a) == a.getLocation()){
								Death.kill(a);
							}
						}
						
					}
				}, 20*5);
			}
		}, 0, 20*1);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().cancelTask(TaskID);
		
		for(Player a:Bukkit.getOnlinePlayers()){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			
			out.writeUTF("Connect");
			out.writeUTF("Lobby-1");
			a.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
		}
		
	}
	
	
}
