package de.rexlNico.GAME.SpawnerPack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Villager;

import de.rexlNico.GAME.Methodes.ItemBuilder;

public class Jump {

	public Jump(Location loc) {
		
		List<Entity> entities = loc.getWorld().getEntities();
		for(Entity items : entities){
			if(items instanceof Item){
				if(items.getLocation().equals(loc)){
					items.remove();
				}
			}
		}
		
		loc.getWorld().dropItem(loc, new ItemBuilder(Material.SLIME_BALL, 1).setName("Jump").build());
	}
}
