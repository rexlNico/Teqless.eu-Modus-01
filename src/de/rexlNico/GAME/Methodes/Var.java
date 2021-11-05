package de.rexlNico.GAME.Methodes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Var {

	public static File file = new File("plugins/modi1/config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static final String pr = "§8● §9§l§oT§feqless.eu §8▏ §7",
			noperm = "§8● §9§l§oT§feqless.eu §8▏ §cDazu hast du keine Berechtigung!",
			error = pr+"Bitte nutze §c/",
			console = "§8● §9§l§oT§feqless.eu §8▏ §cDazu musst du ein Spieler sein!",
			perms = "modi1.";
	
	public static boolean pvp = false;
	
	public static int MAX_PLAYERS = 4;
	public static int MIN_PLAYERS = 2;
	public static int LIVES = 5;
	
	public static ArrayList<Player> playing = new ArrayList<>();
	public static ArrayList<Player> spectating = new ArrayList<>();
	public static ArrayList<Player> voted = new ArrayList<>();
	public static ArrayList<Player> livevote3 = new ArrayList<>();
	public static ArrayList<Player> livevote5 = new ArrayList<>();
	public static ArrayList<Player> livevote7 = new ArrayList<>();
	public static ArrayList<Player> livevote10 = new ArrayList<>();
	public static List<Location> itemSpawner = new ArrayList<>();
	public static HashMap<Player, Integer> lives = new HashMap<Player, Integer>();
	
	
	
			
	
}
