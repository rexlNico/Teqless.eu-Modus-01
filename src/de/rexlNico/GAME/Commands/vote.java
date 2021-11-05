package de.rexlNico.GAME.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rexlNico.GAME.Methodes.Var;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class vote implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length == 1){
				if(args[0].equals("1")){
					if(!Var.voted.contains(p)){
						Var.voted.add(p);
						Var.livevote3.add(p);
					}
				}else if(args[0].equals("2")){
					if(!Var.voted.contains(p)){
						Var.voted.add(p);
						Var.livevote5.add(p);
					}
				}else if(args[0].equals("3")){
					if(!Var.voted.contains(p)){
						Var.voted.add(p);
						Var.livevote7.add(p);
					}
				}else if(args[0].equals("4")){
					if(!Var.voted.contains(p)){
						Var.voted.add(p);
						Var.livevote10.add(p);
					}
				}else{
					p.sendMessage("§1➦ §9Lebenvoting§1");
					TextComponent message1 = new TextComponent("§1➡ §91§1» §73 Leben  §1│ §7Votes §6"+Var.livevote3.size());
					message1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 1"));
					TextComponent message2 = new TextComponent("§1➡ §91§2» §73 Leben  §1│ §7Votes §6"+Var.livevote5.size());
					message2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 2"));
					TextComponent message3 = new TextComponent("§1➡ §91§3» §73 Leben  §1│ §7Votes §6"+Var.livevote7.size());
					message3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 3"));
					TextComponent message4 = new TextComponent("§1➡ §91§4» §73 Leben  §1│ §7Votes §6"+Var.livevote10.size());
					message4.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 4"));
					p.spigot().sendMessage(message1);
					p.spigot().sendMessage(message2);
					p.spigot().sendMessage(message3);
					p.spigot().sendMessage(message4);
					p.sendMessage("§1➦ §9Lebenvoting§1");
				}
			}else{
				p.sendMessage("§1➦ §9Lebenvoting§1");
				TextComponent message1 = new TextComponent("§1➡ §91§1» §73 Leben  §1│ §7Votes §6"+Var.livevote3.size());
				message1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 1"));
				TextComponent message2 = new TextComponent("§1➡ §91§2» §73 Leben  §1│ §7Votes §6"+Var.livevote5.size());
				message2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 2"));
				TextComponent message3 = new TextComponent("§1➡ §91§3» §73 Leben  §1│ §7Votes §6"+Var.livevote7.size());
				message3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 3"));
				TextComponent message4 = new TextComponent("§1➡ §91§4» §73 Leben  §1│ §7Votes §6"+Var.livevote10.size());
				message4.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/vote 4"));
				p.spigot().sendMessage(message1);
				p.spigot().sendMessage(message2);
				p.spigot().sendMessage(message3);
				p.spigot().sendMessage(message4);
				p.sendMessage("§1➦ §9Lebenvoting§1");
			}
		}
		return false;
	}

}
