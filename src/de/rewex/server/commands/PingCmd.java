package de.rewex.server.commands;

import de.rewex.server.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCmd extends Command {

	public PingCmd(String string) {
		super("ping");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			p.sendMessage(new TextComponent("§6•§e● Ping §7| Dein Ping §e" + p.getPing() + "ms"));
			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}
	
	

}
