package de.rewex.server.chat;

import de.rewex.server.Main;
import de.rewex.server.manager.utils.TimeUnit;
import de.rewex.server.servermanager.mute.MuteManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatListeners implements Listener {
	
	/*@EventHandler
	public void onTabComplete(TabCompleteEvent e) {

		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(e.getSender().toString());
		
		if(!p.hasPermission("server.tabcomplete")) { 
			
			//ersetzen durch freunde
			for(ProxiedPlayer all:ProxyServer.getInstance().getPlayers()) {
				if(!e.getSuggestions().contains(all.getName())) {
					e.setCancelled(true);
				}
			}
			
		}
		
	}*/

	public static List<String> urlEnds = new ArrayList<>();

	@EventHandler
	public void onChat(ChatEvent e) {
		if(e.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
			
			if(!(e.getMessage().substring(1).equalsIgnoreCase("/"))) {
				if(MuteManager.getEnd(p.getUniqueId().toString()) <= System.currentTimeMillis()) {
					MuteManager.unmute(p.getUniqueId().toString());
				}
				if(MuteManager.isMuted(p.getUniqueId().toString())) {
					p.sendMessage(new TextComponent(Main.prefix + "§7Du wurdest von §9§lRewex.de§r §cgemuted§8!"));
					p.sendMessage(new TextComponent(Main.prefix + "§7Grund: §c" + MuteManager.getReason(p.getUniqueId().toString())));
					p.sendMessage(new TextComponent(Main.prefix + "§7Verbleibende Zeit: §c" + MuteManager.getRemainingTime(p.getUniqueId().toString())));
					p.sendMessage(new TextComponent(Main.prefix + "§7Gemuted von: " + MuteManager.getMutedFrom(p.getUniqueId().toString())));
					e.setCancelled(true);
				}
			}

			int i = 0;
			urlEnds.add(".com");
			urlEnds.add(".de");
			urlEnds.add(".fr");
			urlEnds.add(".it");
			urlEnds.add(".org");
			urlEnds.add(".net");
			urlEnds.add(".es");
			urlEnds.add(".eu");
			urlEnds.add("http://");
			urlEnds.add("www.");
			for(String s:urlEnds) {
				if(e.getMessage().contains(s)) {
					if(i==0) {
						i++;
					}
					TimeUnit unit = TimeUnit.getUnit("tag");
					long seconds = 1 * unit.getToSecond();
					MuteManager.mute(p.getName(), p.getUniqueId().toString(), null, "Werbung", seconds);
					e.setCancelled(true);
				}
			}



		}
		
	}

	
}
