package de.rewex.server.chat;

import de.rewex.server.Main;
import de.rewex.server.manager.RangManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCmd extends Command {

	public BroadcastCmd(String string) {
		super("broadcast", null, "br");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(!p.hasPermission("server.broadcast")) {
				p.sendMessage(new TextComponent(Main.noperm));
				return;
			}
			
			if(args.length > 0) {
				String msg = "";
				for(int i=0; i< args.length; i++) {
					msg = msg + args[i] + " ";
				}
				
				msg = ChatColor.translateAlternateColorCodes('&', msg);
				ProxyServer.getInstance().broadcast(new TextComponent(Main.prefix + RangManager.getName(p) + " §7» " + msg));
				
			} else {
				p.sendMessage(new TextComponent(Main.prefix + "§c/broadcast <Nachricht>"));
			}
			
		} else {
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}

}
