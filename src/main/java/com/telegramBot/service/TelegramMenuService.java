package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.telegramBot.entity.Icon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TelegramMenuService {

    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger(TelegramMenuService.class);
    public TelegramMenuService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод, который выводит приветственное сообщение
     */

    public void sendWelcomeMessage(Long chatId, String firstName) {

        String message = "Привет, " + firstName + "!  " + Icon.SMILE.get() + "\n" + "\nЯ  твой  " +
                "IT-помощник.  Здесь  собран  \nсписок ответов  на  часто  задаваемые \nвопросы, чтобы" +
                " ты мог быстрее найти \nдля  себя  нужную  информацию.";

        telegramBot.execute(new SendMessage(chatId, message));
    }

    public void getCorporationDoc(Long chatId) {

        String messageDoc = "Здесь должны быть корпоративные документы или ссылки на них. Этап согласовывается.";

        telegramBot.execute(new SendMessage(chatId, messageDoc));
    }

    public void info(Long chatId) {
        String messageInfo = "Если вы не нашли нужную инструкцию(обязательно скажите об этом нам и мы ее добавим)," +
                " или у вас возникли затруднения, вы всегда можете позвонить на общий номер компании," +
                " в группу технической поддержки т: +7(495)-745-70-47 вн.т: 5445 или написать нам по " +
                "адресу helpdesk@acron.ru";

        telegramBot.execute(new SendMessage(chatId, messageInfo));
    }

    public void changePass(Long chatId) {
        logger.info("Processing update: changePass for a user with an ID: " + chatId);

        String path = "src/main/resources/instruction/Change_pass.pdf";
        File changePass = new File(path);
        SendDocument sendDocument = new SendDocument(chatId, changePass);
        sendDocument.caption("В этом документе представлена инструкция смены истекшего пароля");

        telegramBot.execute(sendDocument);
    }

    public void changeCurrentPass(Long chatId) {
        logger.info("Processing update: changeCurrentPass for a user with an ID: " + chatId);

        String path = "src/main/resources/instruction/Change_current_pass.pdf";
        File changingCurrentPass = new File(path);
        SendDocument sendDocument = new SendDocument(chatId, changingCurrentPass);
        sendDocument.caption("В этом документе представлена инструкция смены действующего пароля");

        telegramBot.execute(sendDocument);
    }

    public void changePassVpn(Long chatId) {
        logger.info("Processing update: changePassVpn for a user with an ID: " + chatId);

        String path = "src/main/resources/instruction/Change_pass_VPN.pdf";
        File changePassVpn = new File(path);
        SendDocument sendDocument = new SendDocument(chatId, changePassVpn);
        sendDocument.caption("В этом документе представлена инструкция смены пороля на VPN-подключение");

        telegramBot.execute(sendDocument);
    }

    public void mailSettingsIos(Long chatId) {
        logger.info("Processing update: mailSettingsIos for a user with an ID: " + chatId);

        String path = "src/main/resources/instruction/Installing_mail_settings_IOS.pdf";
        File mailSettingsIos = new File(path);
        SendDocument sendDocument = new SendDocument(chatId, mailSettingsIos);
        sendDocument.caption("В этом документе представлена инструкция настройки почты для устройств с iOS");

        telegramBot.execute(sendDocument);
    }

    public void mailSettingsAndroid(Long chatId) {
        String mailSettingsAndroid = "Здесь должна быть инструкция для устройств Android";

        telegramBot.execute(new SendMessage(chatId, mailSettingsAndroid));

//        logger.info("Processing update: mailSettingsAndroid for a user with an ID: " + chatId);
//
//        String path = "src/main/resources/instruction/Installing_mail_settings_Android.pdf";
//        File mailSettingsAndroid = new File(path);
//        SendDocument sendDocument = new SendDocument(chatId, mailSettingsAndroid);
//        sendDocument.caption("В этом документе представлена инструкция настройки почты для устройств с Android");
//
//        telegramBot.execute(sendDocument);
    }

    public void mailArchiveSettings(Long chatId) {
        String mailArchiveSettings = "Здесь должна быть инструкция по архивированию почты в Outlook, если почтовый " +
                "ящик уже переполнен\"";

        telegramBot.execute(new SendMessage(chatId, mailArchiveSettings));
//        logger.info("Processing update: mailSettingsAndroid for a user with an ID: " +
//                update.callbackQuery().message().chat().id());
//
//        String path = "src/main/resources/instruction/Mail_archive_settings.pdf";
//        File mailArchiveSettings = new File(path);
//        SendDocument sendDocument = new SendDocument(update.callbackQuery().message().chat().id(), mailArchiveSettings);
//        sendDocument.caption("В этом документе представлена инструкция настройки архивирования почты в Outlook," +
//                "если почтовый ящик уже переполнен");
//
//        telegramBot.execute(sendDocument);
    }
}
