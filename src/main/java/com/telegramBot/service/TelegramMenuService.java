package com.telegramBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramMenuService {

    private final TelegramBot telegramBot;


    private final Logger logger = LoggerFactory.getLogger(TelegramMenuService.class);

    private static final String ACRON_IT_HELPER = "Acron_IT_helper";

    public TelegramMenuService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод, который выводит приветственное сообщение и предлагает выбрать дальнейшие действия
     *
     * @param chatId
     */


    public void sendWelcomeMessage(Long chatId, String firstName) {

        String message = "Привет, " + firstName + "!  Тебя  приветсвует  Телеграмм  помощник - " + ACRON_IT_HELPER + ". " +
                "Выбери нужный пункт Главного меню: ";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    /**
     * Метод для пользователя, который впервые в нашем приюте.
     * Метод предлагает выбрать дальнейшие действия.
     *
     * @return
     */


    public String getCorporationDoc() {
        return "Здесь должны быть корпоративные документы или ссылки на них.";
    }



        public String getLinkTicketToIt () {
            return "Здесь должен быть реализован функционал перехода по ссылке https://itsm.acron.ru/sd/operator";
        }



    }
