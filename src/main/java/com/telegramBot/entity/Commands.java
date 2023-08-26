package com.telegramBot.entity;

public enum Commands {

    /**
     * Команды меню клиента телеграмм бота
     * */

    START("/start"),
    START_TEXT("Начало работы с ботом"),
    MAIN_MENU("/menu"),
    MAIN_MENU_TEXT("Вернуться в Главное меню"),
    INFO("/info"),
    INFO_TEXT("Информация о техподдержке"),


    MENU_INSTRUCTION("Меню для инструкций"),
    CORP_DOC("Корпоративные документы"),
    CHANGE_PASS("Смена пароля"),
    BROWSER("Смена пароля через браузер"),

    HEADER(Icon.TOPHAT.get() + "  Выбери нужный пункт меню:  " + Icon.TOPHAT.get()),
    HEADER_MENU("  Выбери нужный пункт меню:  "),

    INVALID_MESSAGE_FORMAT("Неверный формат, для начала работы с ботом напишите /start"),
    ERROR_MESSAGE_TEXT("Извините, но я умею обрабатывать только текст."),
    INSTRUCTION("Меню для инструкций"),
    TICKET_TO_IT("Оставить заявку в ИТ");

    private final String value;
    public String getValue() {
        return value;
    }
    Commands(String value) {
        this.value = value;
    }
}
