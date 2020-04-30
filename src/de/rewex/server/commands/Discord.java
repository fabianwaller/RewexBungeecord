package de.rewex.server.commands;

import de.rewex.server.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Discord extends Command {

	public Discord(String string) {
		super("discord", null, "dc");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			TextComponent tc = new TextComponent();
			tc.setText("§7> §9Discord §7| Klicke hier um unserem Discord Server beizutreten");
			//tc.setItalic(true);
			tc.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://discord.gg/PgTmmR3"));
			tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder("§9Rewex.de").create()));
			p.sendMessage(tc);

			
		} else { 
			sender.sendMessage(new TextComponent(Main.noplayer));
		}
		
	}
	
}
