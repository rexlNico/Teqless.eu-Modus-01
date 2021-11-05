package de.rexlNico.GAME.SpawnerPack;

import java.util.Random;

import org.bukkit.Bukkit;

import de.rexlNico.GAME.Methodes.Var;

public class Spawner {

	public Spawner() {
		int i = new Random().nextInt(3);
		if(i == 0 || i == 1){
			new Jump(Var.itemSpawner.get(new Random().nextInt(Var.itemSpawner.size()-1)));
		}else if(i == 2 | i == 3){
			new Speed(Var.itemSpawner.get(new Random().nextInt(Var.itemSpawner.size()-1)));
		}
		
		
	}
	
}
