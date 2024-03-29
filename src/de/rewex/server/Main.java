package de.rewex.server;

import de.rewex.server.MySQL.GamepassManager;
import de.rewex.server.MySQL.MySQL;
import de.rewex.server.MySQL.stats.Spielzeit;
import de.rewex.server.chat.AutoMessages;
import de.rewex.server.chat.BlockCommands;
import de.rewex.server.chat.BroadcastCmd;
import de.rewex.server.chat.ChatListeners;
import de.rewex.server.chat.ChatclearCmd;
import de.rewex.server.commands.*;
import de.rewex.server.listeners.Fallback;
import de.rewex.server.listeners.ConnectListeners;
import de.rewex.server.listeners.MotdChanger;
import de.rewex.server.manager.ConfigManager;
import de.rewex.server.manager.utils.TimeUnit;
import de.rewex.server.servermanager.FindCmd;
import de.rewex.server.servermanager.ReportCmd;
import de.rewex.server.servermanager.WartungCmd;
import de.rewex.server.servermanager.ban.BanCmd;
import de.rewex.server.servermanager.ban.TempbanCmd;
import de.rewex.server.servermanager.ban.UnbanCmd;
import de.rewex.server.servermanager.kick.KickCmd;
import de.rewex.server.servermanager.mute.MuteCmd;
import de.rewex.server.servermanager.mute.UnmuteCmd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin {
	
	public static String prefix = "§b•§9● Server §7| ";
	public static String cloudpr = "§2•§a● Cloud §7| ";
	public static String coinspr = "§1•§b● Coins §7| ";
	public static String tokenspr = "§2•§a● Tokens §7| ";
	public static String passpr = "§e•§6● Gamepass §7| ";
	public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
	public static String cloudnoperm = cloudpr + "§cDazu hast du keine Rechte§8!";
	public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
	public static String noplayer = "[Server] Nur ein Spieler kann diesen Befehl ausführen";

	Spielzeit spielzeit = new Spielzeit();
	private static Main instance;
	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;

		registerCommands();
		registerListeners();

		MySQL.connect();
		MySQL.createPlayersTable();

		ConfigManager.loadConfiguration();
		AutoMessages.registerTask();
		spielzeit.startCounter();

		GamepassManager.setPass("eccfee88-8fa9-4cc6-b665-1b84e09b5a67", -1L);
	    ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Main.prefix + "§aPlugin aktiviert §7[§9" + getDescription().getVersion() + "§7]"));

	}

	public void onDisable() {
		AutoMessages.unregisterTask();
		ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Main.prefix + "§cPlugin deaktiviert"));
	}
	
	private void registerCommands() {
		PluginManager pm = BungeeCord.getInstance().getPluginManager();

		pm.registerCommand(this, new CoinsCmd("coins"));
		pm.registerCommand(this, new ConnectCmd("dfngz4ierzt7ndrhnfkegg")); //connect
		pm.registerCommand(this, new Discord("discord"));
		pm.registerCommand(this, new GamepassCmd("gamepass"));
		pm.registerCommand(this, new BroadcastCmd("broadcast"));
		pm.registerCommand(this, new ChatclearCmd("chatclear"));
		pm.registerCommand(this, new HubCmd("hub"));
		pm.registerCommand(this, new JoinmeCmd("joinme"));
		pm.registerCommand(this, new PingCmd("ping"));
		pm.registerCommand(this, new TokensCmd("tokens"));
		
		pm.registerCommand(this, new FindCmd("FindCmd"));
		pm.registerCommand(this, new ReportCmd("report"));
		pm.registerCommand(this, new WartungCmd("wartung"));
		
		pm.registerCommand(this, new BanCmd("ban"));
		pm.registerCommand(this, new TempbanCmd("tempban"));
		pm.registerCommand(this, new UnbanCmd("unban"));
		pm.registerCommand(this, new MuteCmd("mute"));
		pm.registerCommand(this, new UnmuteCmd("unmute"));
		
		pm.registerCommand(this, new KickCmd("kick"));
	}
	
	private void registerListeners() {
		PluginManager pm = BungeeCord.getInstance().getPluginManager();
		
		pm.registerListener(this, new ChatListeners());
		
		pm.registerListener(this, new BlockCommands());
		pm.registerListener(this, new Fallback());
		pm.registerListener(this, new ConnectListeners());
		pm.registerListener(this, new MotdChanger());

		
	}
	
}
