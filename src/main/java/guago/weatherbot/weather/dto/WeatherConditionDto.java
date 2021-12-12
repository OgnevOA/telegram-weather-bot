package guago.weatherbot.weather.dto;

import java.text.DecimalFormat;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;

@Getter
public class WeatherConditionDto {
	long dt;
	long sunrise;
	long sunset;
	long moonrise;
	long moonset;
	double moon_phase;
	TemperatureDto temp;
	@JsonAlias({"feels_like"})
	FeelsLikeDto feelsLike;
	double pressure;
	double humidity;
	@JsonAlias({"dew_point"})
	double dewPoint;
	@JsonAlias({"wind_speed"})
	double windSpeed;
	@JsonAlias({"wind_deg"})
	double windDeg;
	List<WeatherDescriptionDto> weather;
	double clouds;
	double uvi;
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.#");
		return weather.get(0).description.substring(0, 1).toUpperCase() + weather.get(0).description.substring(1) + " " + weather.get(0).emojiIcon() + "\n"
				+ "🌡️ " + df.format(feelsLike.getDay()) + "°C\n"
				+ "Max:" + df.format(temp.max) + "°C — Min:" + df.format(temp.min) + "°C\n";
	}
	
	
	
	

}
