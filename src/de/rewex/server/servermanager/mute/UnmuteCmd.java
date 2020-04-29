package de.rewex.server.servermanager.mute;

import de.rewex.server.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class UnmuteCmd extends Command {
	
	public UnmuteCmd(String string) {
		super("unmute");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("server.mute")) {
			sender.sendMessage(new TextComponent(Main.noperm));
			return;
		}
		
		if(args.length != 1) {
			sender.sendMessage(new TextComponent(Main.prefix + "§c/unmute <Spieler>"));
			return;
		}
		
		String playername = args[0];
		if(MuteManager.isMuted(MuteManager.getUUID(playername))) {
			MuteManager.unmute(MuteManager.getUUID(playername));
			sender.sendMessage(new TextComponent(Main.prefix + "Du hast " + playername + " §aerfolgreich §7entmuted§8!"));
			return;
		}
		sender.sendMessage(new TextComponent(Main.prefix + "Der Spieler " + playername + " ist nicht vom Netzwerk gemuted§8!"));
		
	}

}
