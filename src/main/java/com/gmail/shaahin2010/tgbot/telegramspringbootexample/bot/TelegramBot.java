package com.gmail.shaahin2010.tgbot.telegramspringbootexample.bot;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

	private static final String FIRST_NAME = "Shaahin";

	private static final String LAST_NAME = "Yazarloo";

	private static final String PHONE_NUMBER = "+1234567890";

	@Getter
	@Value("${telegram.bot.username}")
	private String botUsername;

	@Getter
	@Value("${telegram.bot.token}")
	private String botToken;

	@Override
	public void onUpdateReceived(Update update) {
		// log the text message
		log.info(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());

		final Long chatId = update.getMessage().getChat().getId();
		SendContact myContact = createMyContact(chatId);
		try {
			super.execute(myContact);
		} catch (TelegramApiException e) {
			log.error(e.getMessage(), e);
		}
	}

	private SendContact createMyContact(final Long chatId) {
		SendContact myContact = new SendContact();
		myContact.setChatId(chatId);
		myContact.setFirstName(FIRST_NAME);
		myContact.setLastName(LAST_NAME);
		myContact.setPhoneNumber(PHONE_NUMBER);
		myContact.setvCard(vCard());
		return myContact;
	}

	private String vCard() {
		VCard vcard = new VCard();
		StructuredName n = new StructuredName();
		n.setFamily(LAST_NAME);
		n.setGiven(FIRST_NAME);
		n.getPrefixes().add("Mr");
		vcard.setStructuredName(n);
		vcard.addTelephoneNumber(new Telephone(PHONE_NUMBER));
		vcard.setFormattedName(MessageFormat.format("{0} {1}", FIRST_NAME, LAST_NAME));
		return Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
	}

}
