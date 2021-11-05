package de.rexlNico.GAME.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.rexlNico.GAME.Main.Main;

public class UsetItems implements Listener{

	@EventHandler
	public void on(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(p.getItemInHand().getType().equals(Material.FEATHER)){
				p.setWalkSpeed((float) 0.8);
				p.getInventory().clear();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						p.setWalkSpeed((float) 0.2);
						
					}
				}, 20*5);
			}else if(p.getItemInHand().getType().equals(Material.SLIME_BALL)){
				p.setVelocity(p.getLocation().getDirection().multiply(5D).setY(1D));
				p.getInventory().clear();
			}
		}
	}
	
}
