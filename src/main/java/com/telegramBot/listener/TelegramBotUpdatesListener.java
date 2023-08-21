package com.telegramBot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.telegramBot.service.TelegramBotService;
import com.telegramBot.service.TelegramMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.telegramBot.repository.TasksRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    public final String START = "/start";

    public static final String MENU_INSTRUCTION = "Меню для инструкций";
    public static final String CORP_DOC = "Получить корпоративные документы";
    public static final String INSTRUCTION_1 = "Почта по веб-интерфейсу";
    public static final String TICKET_TO_IT = "Оставить заявку в ИТ";

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final TasksRepository repository;
    private final TelegramMenuService telegramMenuService;
    private final TelegramBotService telegramBotService;

    private TelegramBotUpdatesListener(TelegramBot telegramBot, TasksRepository repository,
                                       TelegramMenuService telegramMenuService,
                                       TelegramBotService telegramBotService) {
        this.telegramBot = telegramBot;
        this.repository = repository;
        this.telegramMenuService = telegramMenuService;
        this.telegramBotService = telegramBotService;
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
                        SendMessage errorMessage = new SendMessage(update.message().chat().id(), "Извините, но я "
                                + "умею обрабатывать только текст.");
                        telegramBot.execute(errorMessage);
                        return;
                    }

                    Matcher matcher = matchMessage(update);
                    if (START.equals(message) || matcher.matches()) {
                        String firstName = update.message().from().firstName();
                        telegramMenuService.sendWelcomeMessage(chatId, firstName);
                        telegramBotService.firstMenu(chatId);
                    } else {
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Неверный формат, для " +
                                "начала работы с ботом напишите /start"));
                    }
                }

                if (update.callbackQuery() != null && update.message() == null) {

                    chatId = update.callbackQuery().message().chat().id();
                    CallbackQuery callbackQuery = update.callbackQuery();
                    String data = callbackQuery.data();

                    switch (data) {

                        case MENU_INSTRUCTION ->
                            telegramBotService.instructionMenu(chatId);

                        case CORP_DOC ->
                                telegramBot.execute(new SendMessage(chatId, telegramMenuService.getCorporationDoc()));


                    }
                }

            });

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private Matcher matchMessage(Update update) {
        Pattern pattern = Pattern.compile("([\\d\\\\.:\\s]{16})(\\s)([А-яA-z\\s\\d]+)");
        return pattern.matcher(update.message().text());
    }



}
