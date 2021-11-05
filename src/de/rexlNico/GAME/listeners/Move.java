package de.rexlNico.GAME.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.rexlNico.GAME.Main.Main;
import de.rexlNico.GAME.Methodes.Death;
import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;

public class Move implements Listener{
	public static boolean move = false;
	public static ArrayList<Player> canJump = new ArrayList<>();
	public static ArrayList<Player> cooldown = new ArrayList<>();
	public static ArrayList<Player> protection = new ArrayList<>();
	public static ArrayList<Location> glassLocs = new ArrayList<>();
	public static HashMap<Player, Location> lastloc = new HashMap<>();
	public static HashMap<Player, Integer> collor = new HashMap<>();
	public static HashMap<Location, Player> blockP = new HashMap<>();
	public Location loc;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(move == true){
			
			if(!collor.containsKey(p)){
				int c = new Random().nextInt(16);
				for(Player a:Var.playing){
					if(collor.containsKey(a)){
						if(collor.get(a).equals(c)){
							return;
						}
					}
				}
				collor.put(p, c);
			}
			
			
			
			
			
			
			if(p.getLocation().getBlock().getType().equals(org.bukkit.Material.STAINED_GLASS_PANE)){
				if(cooldown.contains(p)){
					return;
				}else{
					if(p.getLocation().getBlock().getData() != Byte.parseByte(String.valueOf(collor.get(p))) && !protection.contains(p)){
						Move.protection.add(p);
						Death.kill(p);
						cooldown.add(p);
					}
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							
							if(cooldown.contains(p)){
							cooldown.remove(p);
							}
						}
					}, 20*5);
				}
			}
			
			Location loc = p.getLocation();
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				
				@SuppressWarnings("deprecation")
				@Override
				
				public void run() {
					
					if(loc.getBlock().getType().equals(Material.AIR)){
						loc.getBlock().setType(Material.STAINED_GLASS_PANE);
						loc.getBlock().setData(Byte.parseByte(String.valueOf(Move.collor.get(p))));
						glassLocs.add(loc);
						blockP.put(loc, p);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								loc.getBlock().setType(Material.AIR);
								glassLocs.remove(loc);
								blockP.remove(loc, p);
							}
						}, 20*3);
					}
				}
			}, 8);
			
			if(lastloc.containsKey(p)){
				lastloc.remove(p);
			}
			lastloc.put(p, p.getLocation());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
				
				@Override
				public void run() {
					if(lastloc.get(p).equals(p.getLocation())){
						Death.kill(p);
					}
				}
			}, 20*15);
			
		}
	}

}
