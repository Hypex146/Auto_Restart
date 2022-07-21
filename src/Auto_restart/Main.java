package Auto_restart;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private FileConfiguration config;
	private boolean enable;
	private int minutesFromMidnightToRestart;
	
	private void check_config() {
		config = getConfig();
		if (!config.isBoolean("enable")) {
			config.set("enable", true);
		}
		enable = config.getBoolean("enable");
		if (!config.isInt("hour")) {
			config.set("hour", 0);
		}
		if (!config.isInt("minute")) {
			config.set("minute", 0);
		}
		minutesFromMidnightToRestart = config.getInt("hour")*60 + config.getInt("minute");
		enable = config.getBoolean("enable");
		saveConfig();
		return;
	}
	
	private void printHelloInConsole() {
		ConsoleCommandSender console = getServer().getConsoleSender();
		console.sendMessage("===========================");
		console.sendMessage("|   |     ___   ___        ");
		console.sendMessage("|   |  0  |  |  |     \\  / ");
		console.sendMessage("|===|     |__|  |__    \\/  ");
		console.sendMessage("|   |  |  |     |      /\\  ");
		console.sendMessage("|   |  |  |     |__   /  \\ ");
		console.sendMessage("===========================");
		console.sendMessage("Restart time: "
				+ config.getInt("hour")+":"+config.getInt("minute"));
	}
	
	@Override
	public void onEnable() {
		check_config();
		if (!enable) {
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		printHelloInConsole();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(
				this, new Runnable() {
					private int timeToRestart = minutesFromMidnightToRestart;
					public void run() {
						MyScheduler.checkForRestart(timeToRestart);
					}
				}, 1200, 600);
	}

}
