package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
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

//    private static final String ACRON_IT_HELPER = "Acron_IT_helper";

    public TelegramMenuService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

   
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

    public void changePass(Update update) {
        logger.info("Запущен метод: changePass для пользователя с ID: " +
                update.callbackQuery().message().chat().id());

        String path = "src/main/resources/instruction/Change_pass.pdf";
        File changePass = new File(path);
        SendDocument sendDocument = new SendDocument(update.callbackQuery().message().chat().id(), changePass);
        sendDocument.caption("В этом документе мы собрали ответы на самые важные вопросы по смене пароля");

        // Выполнение запроса на отправку документа
        telegramBot.execute(sendDocument);
    }
}
