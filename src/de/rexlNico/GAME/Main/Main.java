package de.rexlNico.GAME.Main;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import de.rexlNico.GAME.Commands.Start;
import de.rexlNico.GAME.Commands.setSpawn;
import de.rexlNico.GAME.Commands.vote;
import de.rexlNico.GAME.GameStates.GameState;
import de.rexlNico.GAME.GameStates.GameStateHandler;
import de.rexlNico.GAME.Methodes.Var;

public class Main extends JavaPlugin{

	private static Main plugin;
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void onEnable() {
		plugin = this;	
		Bukkit.getConsoleSender().sendMessage("###############");
		Bukkit.getConsoleSender().sendMessage("§eModus1 Geladen");
		Bukkit.getConsoleSender().sendMessage("###############");
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Load();
		new GameStateHandler();
		GameStateHandler.setGameState(GameState.LOBBY_STATE);
		
			 Var.itemSpawner = (List<Location>) Var.cfg.getList("Spawner");
		
	}
	public static Main getPlugin() {
		return plugin;
	}
	
	private void Load(){
		//Commands
		RegCommand("start", new Start());
		RegCommand("setspawn", new setSpawn());
		RegCommand("vote", new vote());
		//Listeners
		loadListeners();
	}
	
	private void RegCommand(String name,CommandExecutor exe){
		getCommand(name).setExecutor(exe);
	}
    private void loadListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        try {
            for (ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.rexlNico.GAME.listeners")) {
                @SuppressWarnings("rawtypes")
				Class clazz = Class.forName(classInfo.getName());
                if (Listener.class.isAssignableFrom(clazz)) {
                    pluginManager.registerEvents((Listener) clazz.newInstance(), this);
                }
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
	
}
