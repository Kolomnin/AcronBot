package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.telegramBot.entity.Icon;
import org.springframework.stereotype.Service;

import static com.telegramBot.entity.Commands.*;


@Service
public class TelegramBotService {


    private final String buttonInstruction = Icon.CLIPBOARD.get() + "   Инструкции   " + Icon.CLIPBOARD.get();
    private final String buttonCorpDoc = Icon.DOC.get() + "   Корпоративные документы   " + Icon.DOC.get();
    private final String buttonUrlEmail = Icon.EMAIL.get() + "   Почта WEB   " + Icon.EMAIL.get();
    private final String buttonUrlItsm = Icon.SOS.get() + "   СОЗДАТЬ ЗАЯВКУ В ИТ   " + Icon.SOS.get();

    private  final String urlEmail = "https://mail3.acron.ru";
    private  final String urlItsm = "https://itsm.acron.ru/sd";

    private final TelegramBot telegramBot;

    public TelegramBotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

/***
 *  Метод главного меню
 */
    public void firstMenu(Long chatId) {

        SendMessage helloMessage = new SendMessage(chatId, HEADER.getValue());

        InlineKeyboardButton button1 = new InlineKeyboardButton(buttonInstruction);
        button1.callbackData(MENU_INSTRUCTION.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton(buttonCorpDoc);
        button2.callbackData(CORP_DOC.getValue());
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
        SendMessage helloMessage = new SendMessage(chatId, HEADER_MENU.getValue());

        InlineKeyboardButton button1 = new InlineKeyboardButton("Как сменить пароль?");
        button1.callbackData(CHANGE_PASS.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton("Настройка почты для iOS");
        button2.callbackData(CORP_DOC.getValue());
        InlineKeyboardButton button3 = new InlineKeyboardButton("Настройка почты для Android");
        button3.url("https://mail3.acron.ru").callbackData();
        InlineKeyboardButton button4 = new InlineKeyboardButton("Как архивировать почту на ПК");
        button4.url("https://itsm.acron.ru/sd").callbackData();

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

        InlineKeyboardButton button1 = new InlineKeyboardButton("Смена пароля на почте через браузер");
        button1.callbackData(BROWSER.getValue());
        InlineKeyboardButton button2 = new InlineKeyboardButton("Смена пароля до его истечения");
        button2.callbackData(CORP_DOC.getValue());
        InlineKeyboardButton button3 = new InlineKeyboardButton("Изменение пароля для VPN-подключения");
        button3.url("https://mail3.acron.ru").callbackData();

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);



        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }


    /**   Необходимо создать метод для вызова: 1.
     *                                         2. Корпоративные док-ты;
     *                                         3.
     *                                         4. выдача pdf файла с инструкциями;
     *                                         5.
     */

}
