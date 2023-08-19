package com.telegrammBot.entity;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {
    CLIPBOARD(":clipboard:"),
    DOC(":open_file_folder:"),
    TOPHAT(":tophat:"),
    EMAIL(":email: :e-mail:"),
    SOS(":sos:"),
    FLAG(":checkered_flag:");

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Icon(String value) {
        this.value = value;
    }
}
