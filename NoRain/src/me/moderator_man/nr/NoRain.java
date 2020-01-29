package me.moderator_man.nr;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class NoRain extends JavaPlugin
{
	public static boolean isRainEnabled = false;
	
	private WeatherHandler handler = new WeatherHandler();
	
	public void onEnable()
	{
		World world = getServer().getWorld("world");
		
		if (world.getWeatherDuration() > 0)
			world.setWeatherDuration(0);
		
		getServer().getPluginManager().registerEvent(Type.WEATHER_CHANGE, handler, Priority.Normal, this);
		
		System.out.println("NoRain enabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (label.equalsIgnoreCase("enablerain"))
		{
			isRainEnabled = true;
			sender.sendMessage(ChatColor.AQUA + "Rain is now " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + ".");
			return true;
		}
		
		if (label.equalsIgnoreCase("disablerain"))
		{
			sender.sendMessage(ChatColor.AQUA + "Rain is now " + ChatColor.RED + "disabled" + ChatColor.AQUA + ".");
			World world = ((Player) sender).getWorld();
			if (world.hasStorm())
				world.setStorm(false);
			isRainEnabled = false;
			return true;
		}
		
		return false;
	}
	
	public void onDisable()
	{
		System.out.println("NoRain disabled.");
	}
}
