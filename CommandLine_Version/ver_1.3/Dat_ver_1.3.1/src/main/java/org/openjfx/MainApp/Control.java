package org.openjfx.MainApp;

import java.io.IOException;

public class Control {
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        // dictionaryCommandline.dictionaryBasic();
        dictionaryCommandline.dictionaryAdvanced();
    }
}

/*
 * Những phần còn thiếu:
 *  - Kiểm tra tần suất xuất hiện, đưa vào set, tránh lặp lại từ.
 *  - Thêm database từ điển.
 *  - Thêm tiếng Việt có dấu.
 */
