package com.gmail.shaahin2010.tgbot.telegramspringbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TelegramSpringbootExampleApplication {

	public static void main(String[] args) {
		// Add this line to initialize bots context
		ApiContextInitializer.init();
		SpringApplication.run(TelegramSpringbootExampleApplication.class, args);
	}

}
