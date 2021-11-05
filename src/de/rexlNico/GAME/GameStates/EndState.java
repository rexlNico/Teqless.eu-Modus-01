package de.rexlNico.GAME.GameStates;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import de.rexlNico.GAME.listeners.Move;

public class EndState extends GameState{

	@Override
	public void init() {
		Move.move = false;
		for(int i = 0; i<Move.glassLocs.size(); i++){
			Move.glassLocs.get(i).getBlock().setType(Material.AIR);
		}
		Bukkit.reload();
	}

	@Override
	public void end() {
		
	}

}
