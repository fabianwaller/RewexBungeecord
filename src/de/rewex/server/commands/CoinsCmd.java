package de.rewex.server.commands;

import de.rewex.server.Main;
import de.rewex.server.MySQL.stats.PlayersAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CoinsCmd extends Command {

	public CoinsCmd(String string) {
		super("coins");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			int coins = PlayersAPI.getCoins(p.getUniqueId().toString());
			
			p.sendMessage(new TextComponent(Main.coinspr + "Deine Coins: §b" + coins));
			
			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}
}
