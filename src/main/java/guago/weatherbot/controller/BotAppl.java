package guago.weatherbot.controller;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import guago.weatherbot.model.WeatherBot;

public class BotAppl {

	private static final String BOT_NAME = "";
	private static final String BOT_TOKEN = "";

	public static void main(String[] args) {
		try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new WeatherBot(BOT_NAME, BOT_TOKEN));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
