package de.rewex.server.chat;

import java.util.concurrent.TimeUnit;
import de.rewex.server.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public class AutoMessages {
	
	private static ScheduledTask task;
	private static int number;
	
	public static void registerTask() {
		number = 0;
		task = BungeeCord.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if(number == 3) {
					number = 0;
				}
				number++;
				switch(number) {
					case 1:
						ProxyServer.getInstance().broadcast(new TextComponent(Main.prefix + "§7Verwende /discord um auf unseren Discord Server zu gelangen"));
						break;
					case 2:
						ProxyServer.getInstance().broadcast(new TextComponent(Main.prefix + "§7Verdiene mehr Coins mit dem §6Gamepass"));
						break;
					case 3:
						ProxyServer.getInstance().broadcast(new TextComponent(Main.prefix + "§eBetaphase §8- §7Bugreports bitte über Discord einreichen"));
						break;
				}
				
			}
		}, 1, 20, TimeUnit.MINUTES);
	}
	
	public static void unregisterTask() {
		if(task != null) {
			BungeeCord.getInstance().getScheduler().cancel(task);
		}
	}

}
