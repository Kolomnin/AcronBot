package com.telegramBot.entity;

public enum Commands {

    /**
     * Название кнопок/комманд меню клиента телеграмм бота
     * */

    BUTTON_START("/start"),
    START_TEXT("Начало работы с ботом"),
    BUTTON_MAIN_MENU("/menu"),
    MAIN_MENU_TEXT("Вернуться в Главное меню"),
    BUTTON_INFO("/info"),
    INFO_TEXT("Информация о техподдержке"),

    /**
     * Название кнопок/комманд Главного меню
     * */
    BUTTON_INSTRUCTION(Icon.CLIPBOARD.get() + "   Инструкции   " + Icon.CLIPBOARD.get()),
    MENU_INSTRUCTION("Меню для инструкций"),
    BUTTON_CORP_DOC(Icon.DOC.get() + "   Корпоративные документы   " + Icon.DOC.get()),
    CORP_DOC("Корпоративные документы"),
    BUTTON_URL_EMAIL(Icon.EMAIL.get() + "   Почта WEB   " + Icon.EMAIL.get()),
    URL_EMAIL("https://mail3.acron.ru"),
    BUTTON_URL_ITSM(Icon.SOS.get() + "   СОЗДАТЬ ЗАЯВКУ В ИТ   " + Icon.SOS.get()),
    URL_ITSM("https://itsm.acron.ru/sd"),


    CHANGE_PASS("Смена пароля"),
    SETTINGS_MAIL_IOS("Инструкция IOS"),
    SETTINGS_MAIL_ANDROID("Инструкция Android"),
    SETTINGS_ARCHIVE_MAILBOX("Архивирование почты"),

    /**
     * Название кнопок/комманд смены пароля
     * */
    CHANGE_PASS_BROWSER("Смена пароля через браузер"),
    CHANGE_CURRENT_PASSWORD("Смена текущего пароля"),
    CHANGE_PASS_VPN("Смена пароля VPN"),

    /**
     * Заголовки(шапка меню)
     * */
    HEADER(Icon.TOPHAT.get() + "  Выбери нужный пункт меню:  " + Icon.TOPHAT.get()),
    HEADER_MENU(Icon.GREEN_MARK.get() + "  Выбери нужный пункт меню:  " + Icon.GREEN_MARK.get()),

    INVALID_MESSAGE_FORMAT("Неверный формат, для начала работы с ботом напишите /start"),
    ERROR_MESSAGE_TEXT("Извините, но я умею обрабатывать только текст."),

    TICKET_TO_IT("Оставить заявку в ИТ");

    private final String value;
    public String getValue() {
        return value;
    }
    Commands(String value) {
        this.value = value;
    }
}
