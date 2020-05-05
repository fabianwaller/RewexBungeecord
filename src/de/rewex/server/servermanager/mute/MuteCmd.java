package de.rewex.server.servermanager.mute;

import java.util.List;

import de.rewex.server.Main;
import de.rewex.server.manager.utils.TimeUnit;
import de.rewex.server.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MuteCmd extends Command {

	public MuteCmd(String string) {
		super("mute");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("server.mute")) {
			sender.sendMessage(new TextComponent(Main.noperm));
			return;
		}
		
		if(args.length < 3) {
			sender.sendMessage(new TextComponent(Main.prefix + "§c/mute <Spieler> <Zeit> <Einheit> <Grund?>"));
			sender.sendMessage(new TextComponent(Main.prefix + "§7Einheiten §esec§7/§emin§7/§estunde§7/§etag§7/§ewoche"));
			return;
		}
		
		long value = 0L;
		long seconds = 0L;
		try {
			value = Integer.valueOf(args[1]).intValue();
		} catch (NumberFormatException e) {
			sender.sendMessage(new TextComponent(Main.prefix + "§c<Zeit> muss eine Zahl sein"));
			return;
		}
		if(value >= 500L) {
			sender.sendMessage(new TextComponent(Main.prefix + "§c<Zeit> muss unter 500 liegen"));
			return;
		}
		String unitString = args[2];
		TimeUnit unit = null;
		List<String> unitList = TimeUnit.getUnitsAsString();
		if(unitList.contains(unitString.toLowerCase())) {
			unit = TimeUnit.getUnit(unitString);
			seconds = value * unit.getToSecond();
		}
		
		String playername = args[0];
		ProxiedPlayer b = ProxyServer.getInstance().getPlayer(playername);
		if(b == null) {
			sender.sendMessage(new TextComponent(Main.offplayer));
			return;
		}
		if(MuteManager.isMuted(b.getUniqueId().toString())) {
			sender.sendMessage(new TextComponent(Main.prefix + "§cDieser Spieler ist bereits gemuted§8!"));
			return;
		}
		
		String reason = "";
		if(args.length < 4) {
			reason = "Chatverhalten";
		} else {
			for(int i = 3; i<args.length; i++) {
				reason = reason + args[i] + " ";
			}
		}
		
		String mutedfrom = "";
		
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(b.hasPermission("server.antimute")) {
				sender.sendMessage(new TextComponent(Main.prefix + "§cDu kannst diesen Spieler nicht vom Netzwerk muten"));
				return;
			}
			mutedfrom = RangManager.getName(p);
			
		} else {
			mutedfrom = "§cSystem";
		}
		
		MuteManager.mute(b.getName(), b.getUniqueId().toString(), mutedfrom, reason, seconds);
		sender.sendMessage(new TextComponent(Main.prefix + "Du hast " + RangManager.getName(b) + " §7für §c" + value + " " + unit.getName() + " §7wegen §c" + reason + " §7gemutet§8!"));
		
	}

}
