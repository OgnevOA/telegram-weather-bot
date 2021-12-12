package guago.weatherbot.weather.dto;

import lombok.Getter;

@Getter
public class WeatherDescriptionDto {
	int id;
	String main;
	String description;
	String icon;
	
	public String emojiIcon() {
		switch (icon) {
		case "11d": //Thunderstorm
			return "⛈";
		case "09d": //Drizzle
			return "🌦";
		case "10d": //Rain
			return "🌧";
		case "13d": //Snow
			return "🌨";
		case "50d": //Fog
			return "🌫";
		case "01d": //Clear day
			return "☀";
		case "01n": //Clear night
			return "🌒";
		case "02d": //Few clouds
			return "🌤";
		case "02n": //Few clouds
			return "🌤";
		case "03d": //Scattered clouds
			return "⛅";
		case "03n": //Scattered clouds
			return "⛅";
		case "04d": //Broken clouds
			return "🌥";
		case "04n": //Broken clouds
			return "🌥";

		default:
			return "☀";
		}
	}

}
