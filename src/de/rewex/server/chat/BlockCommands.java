package de.rewex.server.chat;

import java.util.List;

import de.rewex.server.Main;
import de.rewex.server.manager.ConfigManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BlockCommands implements Listener {

	@EventHandler
	public void onChat(ChatEvent e) {
		if(e.getSender() instanceof ProxiedPlayer) {
			
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
			
			String msg = e.getMessage();
			String[] cmd = msg.split(" ");
			List<String> blockCMD = ConfigManager.getBlockedCommands();
			for(String blockedCMD : blockCMD) {
				if(blockedCMD.equalsIgnoreCase(cmd[0]) && !p.hasPermission("server.anticmdblock")) {
					p.sendMessage(new TextComponent(Main.noperm));
					e.setCancelled(true);
					return;
				}
			}
			for(String zensur : ConfigManager.getZensurListe()) {
				if(msg.contains(zensur) || msg.contains(zensur.toUpperCase())) {
					e.setCancelled(true);
					p.sendMessage(new TextComponent(Main.prefix + "§cAchte auf deine Wortwahl"));
					return;
				}
			}
		}
	}
	
	
}
