package com.telegrammBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import static com.telegrammBot.listener.TelegramBotUpdatesListener.CORP_DOC;
import static com.telegrammBot.listener.TelegramBotUpdatesListener.MENU_INSTRUCTION;


@Service
public class TelegramBotService {

    private final TelegramBot telegramBot;

    public TelegramBotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

/***
 *  Метод начального меню
 */
    public void firstMenu(Long chatId) {
        SendMessage helloMessage = new SendMessage(chatId, "Главное меню. Выберите интересующий пункт из меню: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Инструкции");
        button1.callbackData(MENU_INSTRUCTION);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Корпоративные документы");
        button2.callbackData(CORP_DOC);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Почта WEB");
        button3.url("https://mail3.acron.ru").callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton("СОЗДАТЬ ЗАЯВКУ В ИТ");
        button4.url("https://itsm.acron.ru/sd").callbackData();
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1,button3);
        keyboard.addRow(button2,button4);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    // Необходимо создать метод для вызова: 1. меню с инструкциями;
    //                                      2. Корпоративные док-ты;
    //                                      3. ссылкок сервисов почта и ITSM;
}
