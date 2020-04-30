package de.rewex.server.servermanager;

import de.rewex.server.Main;
import de.rewex.server.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FindCmd extends Command {

	public FindCmd(String string) {
		super("find");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(!(sender.hasPermission("server.find"))) {
			sender.sendMessage(new TextComponent(Main.noperm));
			return;
		}
		
		if(args.length == 1) {
			
			ProxiedPlayer a = ProxyServer.getInstance().getPlayer(args[0]);
			ServerInfo info = a.getServer().getInfo();
			
			if(a== null || info == null) {
				sender.sendMessage(new TextComponent(Main.offplayer));
				
			} else {
				
				/*if(a.getName().equalsIgnoreCase(sender.getName())) {
					sender.sendMessage(new TextComponent(Main.prefix + "§cDu kannst dich nicht selbst suchen§8!"));
					return;
				}*/
				sender.sendMessage(new TextComponent(Main.prefix + "Der Spieler " + RangManager.getName(a) + " §7befindet sich auf §a" + info.getName()));
			}
			
		} else {
			sender.sendMessage(new TextComponent(Main.prefix + "§c/find <Spieler>"));
		}
		
		
	}
	
	public static String searchPlayer(String playername) {
		ProxiedPlayer a = ProxyServer.getInstance().getPlayer(playername);
		ServerInfo info = a.getServer().getInfo();
		
		
		if(a== null || info == null) {
			return Main.offplayer;
		} else {
			return info.getName();
		}
	}
	

}
