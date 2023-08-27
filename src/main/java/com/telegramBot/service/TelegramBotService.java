package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import static com.telegramBot.entity.Commands.*;


@Service
public class TelegramBotService {

    private final TelegramBot telegramBot;

    public TelegramBotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

/***
 *  Метод главного меню
 */
    public void firstMenu(Long chatId) {

        SendMessage helloMessage = new SendMessage(chatId, HEADER.getValue());

        InlineKeyboardButton button1 = new InlineKeyboardButton(BUTTON_INSTRUCTION.getValue());
        button1.callbackData(MENU_INSTRUCTION.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton(BUTTON_CORP_DOC.getValue());
        button2.callbackData(CORP_DOC.getValue());
        InlineKeyboardButton button3 = new InlineKeyboardButton(BUTTON_URL_EMAIL.getValue());
        button3.url(URL_EMAIL.getValue()).callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton(BUTTON_URL_ITSM.getValue());
        button4.url(URL_ITSM.getValue()).callbackData();
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void instructionMenu(Long chatId) {
        SendMessage helloMessage = new SendMessage(chatId, HEADER_MENU.getValue());

        InlineKeyboardButton button1 = new InlineKeyboardButton("Как сменить пароль?");
        button1.callbackData(CHANGE_PASS.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton("Настройка почты для iOS");
        button2.callbackData(SETTINGS_MAIL_IOS.getValue());
        InlineKeyboardButton button3 = new InlineKeyboardButton("Настройка почты для Android");
        button3.callbackData(SETTINGS_MAIL_ANDROID.getValue());
        InlineKeyboardButton button4 = new InlineKeyboardButton("Как архивировать почту на ПК");
        button4.callbackData(SETTINGS_ARCHIVE_MAILBOX.getValue());

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void changePassMenu(Long chatId) {
        SendMessage helloMessage = new SendMessage(chatId, HEADER_MENU.getValue());

        InlineKeyboardButton button1 = new InlineKeyboardButton("Срок действия пароля истек");
        button1.callbackData(CHANGE_PASS_BROWSER.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton("Смена действующего пароля");
        button2.callbackData(CHANGE_CURRENT_PASSWORD.getValue());
        InlineKeyboardButton button3 = new InlineKeyboardButton("Смена пароля для VPN-подключения");
        button3.callbackData(CHANGE_PASS_VPN.getValue());
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void massageWrong(Long chatId) {
        String wrongMessageText = "Упс... что-то пошло не так, мы скоро решим проблему, не переживайте";

        SendMessage wrongMessage = new SendMessage(chatId, wrongMessageText);
        telegramBot.execute(wrongMessage);
    }
}
