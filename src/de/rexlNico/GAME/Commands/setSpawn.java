package de.rexlNico.GAME.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.rexlNico.GAME.Methodes.Factory;
import de.rexlNico.GAME.Methodes.Var;

public class setSpawn implements CommandExecutor{
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(p.hasPermission(Var.perms+"setspawns")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("lobby")){
						Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Lobby");
						p.sendMessage(Var.pr+"Du hast den Spawn für die Lobby gesetzt.");
					}else if(args[0].equalsIgnoreCase("Spectator")){
						Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Spectator");
						p.sendMessage(Var.pr+"Du hast den Spawn für die Spectator gesetzt.");
					}else if(args[0].equalsIgnoreCase("ItemSpawner")){
						List<Location> locs = null;
						try{
						locs = (List<Location>) Var.cfg.getList("Spawner");
						}catch(Exception e){
							locs = new ArrayList<>();
						}
						if(locs == null){
							locs = new ArrayList<>();
						}
						locs.add(p.getLocation());
						
						Var.cfg.set("Spawner", locs);
						try {
							Var.cfg.save(Var.file);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						p.sendMessage("§7-------------------");
						p.sendMessage("");
						p.sendMessage("/setspawn Lobby");
						p.sendMessage("/setspawn Lobby");
						p.sendMessage("/setspawn ItemSpawner");
						p.sendMessage("/setspawn Spawn <1-8>");
						p.sendMessage("");
						p.sendMessage("§7-------------------");
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("Spawn")){
						int i = 0;
						try {
							i = Integer.parseInt(args[1]);
						} catch (Exception e) {
							p.sendMessage(Var.pr+"Bitte gib eine Zahl von 1-8 an!");
						}
						if(i <= 8 && i >= 1){
							
							Factory.CreateConfigLocation(p.getLocation(), Var.cfg, Var.file, "Spawn."+i);
							p.sendMessage(Var.pr+"Du hast den Spawn "+i+" von 8 gesetzt.");
							
						}else{
							p.sendMessage(Var.pr+"Bitte gib eine Zahl von 1-8 an!");
						}
					}else{
						p.sendMessage("§7-------------------");
						p.sendMessage("");
						p.sendMessage("/setspawn Lobby");
						p.sendMessage("/setspawn Spectator");
						p.sendMessage("/setspawn ItemSpawner");
						p.sendMessage("/setspawn Spawn <1-8>");
						p.sendMessage("");
						p.sendMessage("§7-------------------");
					}
				}else{
					p.sendMessage("§7-------------------");
					p.sendMessage("");
					p.sendMessage("/setspawn Lobby");
					p.sendMessage("/setspawn Spectator");
					p.sendMessage("/setspawn ItemSpawner");
					p.sendMessage("/setspawn Spawn <1-8>");
					p.sendMessage("");
					p.sendMessage("§7-------------------");
				}
			}else{
				p.sendMessage(Var.noperm);
			}
		}else{
			sender.sendMessage(Var.console);
		}
		return false;
	}

}
