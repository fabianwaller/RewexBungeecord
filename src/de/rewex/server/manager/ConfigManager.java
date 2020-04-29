package de.rewex.server.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import de.rewex.server.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {
	
	public static File file = new File("plugins/RewexBungeecord/config.yml");
	public static String path = "Server.";
	
	public static void loadConfiguration() {
		if(!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdir();
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
				
				set("wartung", Boolean.valueOf(true));
				set("grund", "Wartungsmodus");
				set("fortschritt", Integer.valueOf(0));
				
		        List<String> blockcmd = getStringList("commands_blocked");
		        blockcmd.add("/?");
		        blockcmd.add("/pl");
		        blockcmd.add("/server");
		        blockcmd.add("/bungee");
		        blockcmd.add("/skript");
		        blockcmd.add("/plugins");
		        blockcmd.add("/plugin");
		        blockcmd.add("/version");
		        blockcmd.add("/spigot");
		        blockcmd.add("/op");
		        blockcmd.add("/bukkit");
		        blockcmd.add("/bukkit:?");
		        blockcmd.add("/hd");
		        blockcmd.add("/help");
		       // ConfigManager.set("commands_blocked", blockcmd);
		        
		        List<String> zensur = getStringList("zensur");
		        zensur.add("schwanz");
		        zensur.add("penis");
		        zensur.add("huso");
		        zensur.add("penner");
		        zensur.add("nazi");
		        zensur.add("arsch");
		        zensur.add("arschloch");
		        zensur.add("doof");
		        zensur.add("hacker");
		        zensur.add("eZ");
		        zensur.add("ez");
		        zensur.add("Ez");
		        zensur.add("Easy");
		        zensur.add("easy");
		        zensur.add("sex");
		        zensur.add("Sex");
		        zensur.add("Vögeln");
		        zensur.add("vögeln");
		        zensur.add("Ficken");
		        zensur.add("ficken");
		        zensur.add("fick");
		        zensur.add("Fick");
		        //ConfigManager.set("zensur", zensur);

			} catch(IOException ex) {
				Logger.getLogger("Loading Error: ConfigManager.java");
			}
		}
		reloadConfiguration(false);
		
	}
	
	@SuppressWarnings("unused")
	public static void reloadConfiguration(boolean done) {
		try {
			if(!done) {
				Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
				return;
			}
			loadConfiguration();
		} catch (IOException ex) {
			Logger.getLogger("Reload Error: ConfigManager.java");
		}
	}
	
	  
	public static void set(String newPath, Object value) {
		try {
	      Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	      config.set(path + newPath, value);
	      ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
	    } catch (IOException ex) {
			Logger.getLogger("setMethod: ConfigManager.java");
	    }
	}
	
	public static boolean getBoolean(String newPath) throws IOException {
		Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		return config.getBoolean(path + newPath);
	}
  
	public static String getString(String newPath) throws IOException {
		Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		return config.getString(path + newPath);
	}
  
	public static int getInt(String newPath) throws IOException {
		Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		return config.getInt(path + newPath);
	}
  
	public static List<String> getStringList(String newPath) throws IOException {
		Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		return config.getStringList(path + newPath);
	}
	
	///////////////////////////////////////////////
	
	public static boolean getWartung() {
	    try {
	      return getBoolean("wartung");
	    } catch (IOException ex) {
	    	Logger.getLogger("getWartung: ConfigManager.java");
	    }
	    return true;
	}
	
	public static String getWartungGrund() {
	    try {
	      return getString("grund");
	    } catch (IOException ex) {
	    	Logger.getLogger("getWartung: ConfigManager.java");
	    }
	    return "Wartungsmodus";
	}
	
	public static int getWartungFortschritt() {
	    try {
	      return getInt("fortschritt");
	    } catch (IOException ex) {
	    	Logger.getLogger("getWartungFortschritt: ConfigManager.java");
	    }
	    return 0;
	}
	
	public static void setWartung(boolean status) {
		set("wartung", status);
	}
	
	public static List<String> getBlockedCommands() {
	    try {
	      return getStringList("commands_blocked");
	    } catch (IOException ex) {
	    	Logger.getLogger("Commands Blocked: ConfigManager.java");
	    }
	    return null;
	}
	
	public static List<String> getZensurListe() {
	    try {
	      return getStringList("zensur");
	    } catch (IOException ex) {
	    	Logger.getLogger("Commands Blocked: ConfigManager.java");
	    }
	    return null;
	}
	
}
