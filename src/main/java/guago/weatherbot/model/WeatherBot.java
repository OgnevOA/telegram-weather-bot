package guago.weatherbot.model;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import guago.weatherbot.geocoding.Geocode;
import guago.weatherbot.geocoding.dto.CityDescriptionDto;
import guago.weatherbot.weather.Forecast;
import guago.weatherbot.weather.dto.WeatherResponceDto;

public class WeatherBot extends TelegramLongPollingBot {
	private final String BOT_NAME;
	private final String BOT_TOKEN;
	private final String WEATHER_API_KEY = "";

	public WeatherBot(String botName, String botToken) {
		super();
		BOT_NAME = botName;
		BOT_TOKEN = botToken;
//		BotCommand command = new BotCommand("/test", "Start conversation");
	}

	@Override
	public void onUpdateReceived(Update update) {
		//TODO add thread pool
		checkMessage(update.getMessage(), WEATHER_API_KEY);
	}

	private void checkMessage(Message message, String weatherKey) {
		System.out.println("New request from " + message.getFrom().getUserName());
		SendMessage response = new SendMessage();
		CityDescriptionDto city = new CityDescriptionDto();
		WeatherResponceDto weather = new WeatherResponceDto();
		if (message.getLocation() == null) {
			city = Geocode.getLocation(message.getText(), weatherKey);
		} else {
			city = Geocode.getLocation(message.getLocation().getLatitude(), message.getLocation().getLongitude(), weatherKey);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		weather = Forecast.getWeatherForecast(city.getLat(), city.getLon(), weatherKey, message.getFrom().getLanguageCode());
		response.setText(buildMessage(weather, city, message.getFrom().getLanguageCode()));
		response.setChatId(message.getChatId().toString());
		try {
			execute(response);
		} catch (TelegramApiException e) {
			// логируем сбой Telegram Bot API, использу€ userName
		}
	}

	private String buildMessage(WeatherResponceDto weather, CityDescriptionDto city, String locale) {
		StringBuilder answer = new StringBuilder();
		answer.append(city.getLocalNames().get(locale) + ": \r\n");
		answer.append("Today: \r\n");
		answer.append(weather.getDaily().get(0));
		answer.append("Tomorrow: \r\n");
		answer.append(weather.getDaily().get(1));
		return answer.toString();
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return BOT_TOKEN;
	}

}
