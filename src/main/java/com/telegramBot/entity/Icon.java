package com.telegramBot.entity;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    CLIPBOARD(":clipboard:"),
    DOC(":open_file_folder:"),
    TOPHAT(":tophat:"),
    EMAIL(":email:"),
    SOS(":sos:"),
    SMILE(":hugging:"),
    GREEN_MARK(":white_check_mark:");


    private final String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Icon(String value) {
        this.value = value;
    }
}


