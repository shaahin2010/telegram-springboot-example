package com.gmail.shaahin2010.tgbot.telegramspringbootexample;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BotConfigurationTest {

	@Value("${telegram.bot.username}")
	private String username;

	@Test
	public void botNameMustConfiged() {
		Assert.assertNotNull(username);
		assertTrue(username.isEmpty());
	}
}
