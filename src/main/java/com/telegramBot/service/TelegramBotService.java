package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.telegramBot.entity.Icon;
import liquibase.pro.packaged.S;
import org.springframework.stereotype.Service;

import static com.telegramBot.listener.TelegramBotUpdatesListener.CORP_DOC;
import static com.telegramBot.listener.TelegramBotUpdatesListener.MENU_INSTRUCTION;


@Service
public class TelegramBotService {

    private final String header = Icon.TOPHAT.get() + "  Выбери нужный пункт меню:  " + Icon.TOPHAT.get();
    private final String buttonInstruction = Icon.CLIPBOARD.get() + "   Инструкции   " + Icon.CLIPBOARD.get();
    private final String buttonCorpDoc = Icon.DOC.get() + "   Корпоративные документы   " + Icon.DOC.get();
    private final String buttonUrlEmail = Icon.EMAIL.get() + "   Почта WEB   " + Icon.EMAIL.get();
    private final String buttonUrlItsm = Icon.SOS.get() + "   СОЗДАТЬ ЗАЯВКУ В ИТ   " + Icon.SOS.get();

    private static final String urlEmail = "https://mail3.acron.ru";
    private static final String urlItsm = "https://itsm.acron.ru/sd";

    private final TelegramBot telegramBot;

    public TelegramBotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

/***
 *  Метод главного меню
 */
    public void firstMenu(Long chatId) {

        SendMessage helloMessage = new SendMessage(chatId, header);

        InlineKeyboardButton button1 = new InlineKeyboardButton(buttonInstruction);
        button1.callbackData(MENU_INSTRUCTION);
        InlineKeyboardButton button2 = new InlineKeyboardButton(buttonCorpDoc);
        button2.callbackData(CORP_DOC);
        InlineKeyboardButton button3 = new InlineKeyboardButton(buttonUrlEmail);
        button3.url(urlEmail).callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton(buttonUrlItsm);
        button4.url(urlItsm).callbackData();
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void instructionMenu(Long chatId) {
        SendMessage helloMessage = new SendMessage(chatId, "Выберите нужную инструкцию: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Инструкция 1");
        button1.callbackData(MENU_INSTRUCTION);
        InlineKeyboardButton button2 = new InlineKeyboardButton("📂 Корпоративные документы");
        button2.callbackData(CORP_DOC);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Инструкция 3");
        button3.url("https://mail3.acron.ru").callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton("Инструкция 4");
        button4.url("https://itsm.acron.ru/sd").callbackData();
        InlineKeyboardButton button5 = new InlineKeyboardButton("Вернуться в Главное меню");

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);


        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    /**   Необходимо создать метод для вызова: 1. меню с инструкциями;
     *                                         2. Корпоративные док-ты;
     *                                         3. Меню команд в боте;
     *                                         4. выдача pdf файла с инструкциями;
     *                                         5. Приветствие c именем кто пользуетсят ботом.
     */

}
