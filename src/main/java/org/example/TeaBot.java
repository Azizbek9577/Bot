package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TeaBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "issiqChoy_bot";
    }

    @Override
    public String getBotToken() {
        return "7708054555:AAFmMNpHWdI3TkKXhhKSt8470c2GFdqvXUg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            String userName = update.getMessage().getFrom().getFirstName();

            if (text.equals("Choy damlash")) {
                sendTeaMessage(chatId, userName);
            } else {
                sendDefaultMessage(chatId);
            }
        }
    }

    private void sendTeaMessage(String chatId, String userName) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Marhamat, " + userName + "! ☕");
            execute(message);

            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            InputFile photoFile = new InputFile("https://upload.wikimedia.org/wikipedia/commons/3/3d/Tea_pot.jpg");
            photo.setPhoto(photoFile);
            execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendDefaultMessage(String chatId) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Iltimos, pastdagi tugmadan foydalaning!");

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton("Choy damlash"));
            keyboard.add(row);

            keyboardMarkup.setKeyboard(keyboard);
            keyboardMarkup.setResizeKeyboard(true);
            message.setReplyMarkup(keyboardMarkup);

            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}