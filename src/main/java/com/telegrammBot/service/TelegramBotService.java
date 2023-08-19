package com.telegrammBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.telegrammBot.entity.Icon;
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
        SendMessage helloMessage = new SendMessage(chatId, Icon.TOPHAT.get()
                + "                   "+
                " ГЛАВНОЕ МЕНЮ " + "                   "
                + Icon.TOPHAT.get());

        InlineKeyboardButton button1 = new InlineKeyboardButton(Icon.CLIPBOARD.get() + "   Инструкции");
        button1.callbackData(MENU_INSTRUCTION);
        InlineKeyboardButton button2 = new InlineKeyboardButton(Icon.DOC.get() + "   Корпоративные документы");
        button2.callbackData(CORP_DOC);
        InlineKeyboardButton button3 = new InlineKeyboardButton(Icon.EMAIL.get() + "   Почта WEB");
        button3.url("https://mail3.acron.ru").callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton(Icon.SOS.get() + "   СОЗДАТЬ ЗАЯВКУ В ИТ");
        button4.url("https://itsm.acron.ru/sd").callbackData();
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
        InlineKeyboardButton button2 = new InlineKeyboardButton("Инструкция 2");
        button2.callbackData(CORP_DOC);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Инструкция 3");
        button3.url("https://mail3.acron.ru").callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton("Инструкция 4");
        button4.url("https://itsm.acron.ru/sd").callbackData();
        InlineKeyboardButton button5 = new InlineKeyboardButton("Вернуться в Главное меню");
        button5.callbackData();
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);
        keyboard.addRow(button5);

        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    // Необходимо создать метод для вызова: 1. меню с инструкциями;
    //                                      2. Корпоративные док-ты;

}
