package de.rewex.server.chat;

import de.rewex.server.Main;
import de.rewex.server.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ChatclearCmd extends Command {

	public ChatclearCmd(String string) {
		super("chatclear", null, "cc");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			if(args.length == 0) {
				
				clearChat(p);
				p.sendMessage(new TextComponent(Main.prefix + "Dein §aChat §7wurde geleert"));
				
			} else if (args.length == 1) {
				
				if(!(p.hasPermission("server.chatclear"))) {
					p.sendMessage(new TextComponent(Main.noperm));
					return;
				}
				
				if(args[0].equalsIgnoreCase("@all")) {
					
					int i=0;
					while(i<200) {
						for(ProxiedPlayer all : p.getServer().getInfo().getPlayers()) {
							all.sendMessage(new TextComponent(" "));
						}
						i++;
					}
					for(ProxiedPlayer all : p.getServer().getInfo().getPlayers()) {
						all.sendMessage(new TextComponent(Main.prefix + "§7Der §aChat §7wurde von " + RangManager.getName(p) + " §7geleert"));
					}
					
				} else {
					
					p.sendMessage(new TextComponent(Main.prefix + "§c7chatclear | /chatclear @all"));
					
				}
				
			} else {
				
				p.sendMessage(new TextComponent(Main.prefix + "§c7chatclear | /chatclear @all"));
				
			}
			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}
	
	public static void clearChat(ProxiedPlayer p) {
		for(int i=0; i<200; i++) {
			p.sendMessage(new TextComponent(" "));
		}
	}
	

}
