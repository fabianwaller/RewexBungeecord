package de.rewex.server.commands;

import de.rewex.server.Main;
import de.rewex.server.MySQL.GamepassManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GamepassCmd extends Command {

	public GamepassCmd(String string) {
		super("gamepass", null, "pass");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(GamepassManager.hasPass(p.getUniqueId().toString())) {
				
				p.sendMessage(new TextComponent(Main.passpr + "§7Dein §6Gamepass §7ist §aaktiviert"));
				p.sendMessage(new TextComponent(Main.passpr + "§7Verbleibende Zeit: " + GamepassManager.getRemainingTime(p.getUniqueId().toString())));
				
			} else {
				p.sendMessage(new TextComponent(Main.passpr + "§7Dein §6Gamepass §7ist §cdeaktiviert§7, aktiviere ihn zuerst im Shop"));
			}
			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}

}
