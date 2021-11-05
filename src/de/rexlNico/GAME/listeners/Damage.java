package de.rexlNico.GAME.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener{

	@EventHandler
	public void on(EntityDamageEvent e){
		e.setCancelled(true);
	}
	
}
