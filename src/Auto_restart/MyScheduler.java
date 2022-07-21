package Auto_restart;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public abstract class MyScheduler {
	
	public static void checkForRestart(int timeToRestart) {
		Date dateNow = new Date();
		SimpleDateFormat formatForHours = new SimpleDateFormat("H");
		int hours = Integer.parseInt(formatForHours.format(dateNow));
		SimpleDateFormat formatForMinutes = new SimpleDateFormat("m");
		int minutes = Integer.parseInt(formatForMinutes.format(dateNow));
		int minutesFromMidnight = hours*60 + minutes;
		if (minutesFromMidnight==timeToRestart) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.getServer().dispatchCommand(console, "restart");
			return;
		}
		if ((minutesFromMidnight+5)%1440==timeToRestart) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.getServer().dispatchCommand(console, 
					"say ƒо перезагрузки сервера менее 5 минут!");
			return;
		}
		if ((minutesFromMidnight+1)%1440==timeToRestart) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.getServer().dispatchCommand(console, 
					"say ƒо перезагрузки сервера менее 1 минуты!");
			return;
		}
	}

}
