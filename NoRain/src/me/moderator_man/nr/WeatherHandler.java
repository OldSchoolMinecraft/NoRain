package me.moderator_man.nr;

import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

public class WeatherHandler extends WeatherListener
{
	public void onWeatherChange(WeatherChangeEvent event)
	{
		if (!NoRain.isRainEnabled)
			event.setCancelled(true);
	}
}
