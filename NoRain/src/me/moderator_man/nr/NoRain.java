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
		Player player = (Player) sender;
		
		if (label.equalsIgnoreCase("enablerain"))
		{
			if (!player.isOp() && !player.hasPermission("osm.norain"))
			{
				sender.sendMessage(ChatColor.RED + "You aren't allowed to enable rain!");
				return true;
			}
			
			isRainEnabled = true;
			sender.sendMessage(ChatColor.AQUA + "Rain is now " + ChatColor.GREEN + "enabled" + ChatColor.AQUA + ".");
			return true;
		}
		
		if (label.equalsIgnoreCase("disablerain"))
		{
			if (!player.isOp() && !player.hasPermission("osm.norain"))
			{
				sender.sendMessage(ChatColor.RED + "You aren't allowed to disable rain!");
				return true;
			}
			
			sender.sendMessage(ChatColor.AQUA + "Rain is now " + ChatColor.RED + "disabled" + ChatColor.AQUA + ".");
			World world = player.getWorld();
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
