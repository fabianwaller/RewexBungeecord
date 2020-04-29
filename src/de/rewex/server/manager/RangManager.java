package de.rewex.server.manager;

import de.rewex.server.MySQL.stats.PlayersAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class RangManager {
	
	public static String getName(ProxiedPlayer p) {
		return getColor(p) + p.getName();
	}
	
	public static String getColor(ProxiedPlayer p) {
		if(p.hasPermission("server.admin")) {
			return "§4";
			
		} else if(p.hasPermission("server.mod")) {
			return "§b";
			
		} else if(p.hasPermission("server.dev")) {
			return "§d";
			
		} else if(p.hasPermission("server.builder")) {
			return "§a";
			
		} else {
			return "§7";
		}
	}
	
	public static String getTeamRang(ProxiedPlayer p) {
		if(p.hasPermission("server.admin")) {
			return "§4Admin";
			
		} else if(p.hasPermission("server.mod")) {
			return "§bModerator";
			
		} else if(p.hasPermission("server.dev")) {
			return "§dDeveloper";
			
		} else if(p.hasPermission("server.builder")) {
			return "§aBuilder";
			
		} else {
			return "§7Spieler";
		}
	}
	
	public static String getRang(ProxiedPlayer p) {
		String rang = PlayersAPI.getRang(p.getUniqueId().toString());
		
		if(rang.equalsIgnoreCase("Legende")) {
			return "§9§lLEGENDE ";
			
		} else if(rang.equalsIgnoreCase("Titan")) {
			return "§e§lTITAN ";
			
		} if(rang.equalsIgnoreCase("Champion")) {
			return "§c§lCHAMPION ";
			
		} if(rang.equalsIgnoreCase("Meister")) {
			return "§5§lMEISTER ";
			
		} 
		return "";
	}
	
	public static String getRangtoshow(ProxiedPlayer p) {
		String rang = PlayersAPI.getRang(p.getUniqueId().toString());
		
		if(rang.equalsIgnoreCase("Legende")) {
			return "§9§lLegende";
			
		} else if(rang.equalsIgnoreCase("Titan")) {
			return "§e§lTitan";
			
		} if(rang.equalsIgnoreCase("Champion")) {
			return "§c§lChampion";
			
		} if(rang.equalsIgnoreCase("Meister")) {
			return "§5§lMeister";
			
		} else {
			return "§7§lSpieler";
		}
	}

}
