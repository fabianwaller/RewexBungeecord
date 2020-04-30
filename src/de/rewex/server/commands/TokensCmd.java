package de.rewex.server.commands;

import de.rewex.server.Main;
import de.rewex.server.MySQL.stats.PlayersAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TokensCmd extends Command {

	public TokensCmd(String string) {
		super("tokens");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			int tokens = PlayersAPI.getTokens(p.getUniqueId().toString());
			
			p.sendMessage(new TextComponent(Main.tokenspr + "Deine Tokens: §a" + tokens));
			
			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}

}
