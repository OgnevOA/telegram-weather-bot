package guago.weatherbot.weather.dto;

import lombok.Getter;

@Getter
public class TemperatureDto {
	double day;
	double min;
	double max;
	double night;
	double eve;
	double morn;
}
