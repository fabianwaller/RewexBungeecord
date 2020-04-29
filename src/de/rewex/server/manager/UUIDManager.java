package de.rewex.server.manager;

import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UUIDManager {
	
	public static ProxiedPlayer getPlayer(String playername) {
		for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
			if (players.getName().toLowerCase().equals(playername.toLowerCase())) {
				return players;
			}
		}	
		return null;
	}
	  	  
	public static UUID getUUID(String playername) {
		ProxiedPlayer p = getPlayer(playername);
		if (p != null) {
			return p.getUniqueId();
	    }
	    return null;
	}
	


}
