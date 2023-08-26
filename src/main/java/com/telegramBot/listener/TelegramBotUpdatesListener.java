package com.telegramBot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.telegramBot.service.TelegramBotService;
import com.telegramBot.service.TelegramMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.telegramBot.entity.Commands.*;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final String INSTRUCTION = "Меню для инструкций";
    private final String DOC = "Корпоративные документы";
    private final String PASS = "Смена пароля";
    private final String CHANGE_PASS_BR = "Смена пароля через браузер";

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final TelegramMenuService telegramMenuService;
    private final TelegramBotService telegramBotService;

    private TelegramBotUpdatesListener(TelegramBot telegramBot,
                                       TelegramMenuService telegramMenuService,
                                       TelegramBotService telegramBotService) {
        this.telegramBot = telegramBot;
        this.telegramMenuService = telegramMenuService;
        this.telegramBotService = telegramBotService;

        telegramBot.execute(new SetMyCommands(
                new BotCommand(START.getValue(), START_TEXT.getValue()),
                new BotCommand(MAIN_MENU.getValue(), MAIN_MENU_TEXT.getValue()),
                new BotCommand(INFO.getValue(), INFO_TEXT.getValue())
        ));
    }

    Long chatId;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {

            updates.forEach(update -> {
                logger.info("Processing update: {}", update);

                if (update.message() != null) {
                    chatId = update.message().chat().id();
                    String message = update.message().text();

                    if (update.message().photo() != null
                            || update.message().sticker() != null
                            || update.message().video() != null
                            || update.message().audio() != null
                            || update.message().document() != null
                            || update.message().animation() != null) {
                        SendMessage errorMessage = new SendMessage(update.message().chat().id(),
                                ERROR_MESSAGE_TEXT.getValue());
                        telegramBot.execute(errorMessage);
                        return;
                    }
                    /**
                     * Функционал кнопки Меню
                     * Провека Reg-Ex;
                     * Кнопки старт, главное меню, информация;
                     */

                    Matcher matcher = matchMessage(update);
                    if (START.getValue().equals(message) || matcher.matches()) {
                        String firstName = update.message().from().firstName();
                        telegramMenuService.sendWelcomeMessage(chatId, firstName);
                        telegramBotService.firstMenu(chatId);

                    } else if (MAIN_MENU.getValue().equals(message) || matcher.matches()) {
                        telegramBotService.firstMenu(chatId);

                    } else if (INFO.getValue().equals(message) || matcher.matches()) {
                        telegramMenuService.info(chatId);

                    } else {
                        telegramBot.execute(new SendMessage(update.message().chat().id(),
                                INVALID_MESSAGE_FORMAT.getValue()));
                    }
                }

                if (update.callbackQuery() != null && update.message() == null) {

                    chatId = update.callbackQuery().message().chat().id();
                    CallbackQuery callbackQuery = update.callbackQuery();
                    String data = callbackQuery.data();

                    switch (data) {
                        case INSTRUCTION -> telegramBotService.instructionMenu(chatId);
                        case DOC -> telegramMenuService.getCorporationDoc(chatId);
                        case PASS -> telegramBotService.changePassMenu(chatId);
                        case CHANGE_PASS_BR -> telegramMenuService.changePass(update);
                    }
                }
            });

        } catch (RuntimeException e) {
            System.out.println("Something went wrong: " + e);

        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private Matcher matchMessage(Update update) {
        Pattern pattern = Pattern.compile("([\\d\\\\.:\\s]{16})(\\s)([А-яA-z\\s\\d]+)");
        return pattern.matcher(update.message().text());
    }
}
