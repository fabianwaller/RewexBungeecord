package de.rewex.server.listeners;

import de.rewex.server.manager.ConfigManager;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MotdChanger implements Listener {
	
	@EventHandler
	public void onPing(ProxyPingEvent e) {
		ServerPing con = e.getResponse();
		if(ConfigManager.getWartung() == true) {
			con.setDescriptionComponent(new TextComponent("§b•§9● Rewex.de §8● §r§7Minecraft Netzwerk §8➟ §a1.8§7-§a1.15.2§r\n"
					+ "§8> §cWartungsarbeiten "));
			con.setVersion(new ServerPing.Protocol("§8➜ §cWartungen", 2));
		} else {
			con.setDescriptionComponent(new TextComponent("§b•§9● Rewex.de §8● §r§7Minecraft Netzwerk §8➟ §a1.8§7-§a1.15.2§r\n"
					+ ConfigManager.getStatus().replace("&", "§")));
		}
		e.setResponse(con);

	}
	

	
}
