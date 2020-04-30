package de.rewex.server.servermanager;

import de.rewex.server.Main;
import de.rewex.server.manager.RangManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCmd extends Command {

	public ReportCmd(String name) {
		super("report");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new TextComponent(Main.noplayer));
			return;
		}
		
		ProxiedPlayer p = (ProxiedPlayer) sender;
		
		if(args.length > 1) {
			
			String player = args[0];
			if(player.equalsIgnoreCase(p.getName())) {
				p.sendMessage(new TextComponent(Main.prefix + "§cDu kannst dich nicht selbst reporten"));
				return;
				
			} else if(ProxyServer.getInstance().getPlayer(player) == null) {
				p.sendMessage(new TextComponent(Main.offplayer));
				return;
				
			} else {
				ProxiedPlayer rp = ProxyServer.getInstance().getPlayer(player);
				args[0]= "";
				StringBuilder stringbuilder = new StringBuilder();
				for(String arg : args) {
					stringbuilder.append(arg).append(" ");
				}
				String reason = stringbuilder.toString();
				for(ProxiedPlayer team : Main.getInstance().getProxy().getPlayers()) {
					if(team.hasPermission("server.seereports")) {
						team.sendMessage(new TextComponent(Main.prefix + "Der Spieler " + RangManager.getName(rp) + " §7wurde von " + RangManager.getName(p) + " §7wegen§c" + reason + "§7reportet"));

						TextComponent tc = new TextComponent();
						tc.setText(Main.prefix + "§aKlicke hier um den Report zu bearbeiten");
						tc.setItalic(true);
						tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/connect " + FindCmd.searchPlayer(rp.getName())));
						tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
								new ComponentBuilder("§7Server: §a" + FindCmd.searchPlayer(rp.getName())).create())); 
						team.sendMessage(tc);
					}
				}
			}
			
		} else {
			p.sendMessage(new TextComponent(Main.prefix + "§c/report <Spieler> <Grund>"));
		}
			
	}

}
