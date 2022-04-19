package intercom_bot.bot;

import intercom_bot.data.initializer.DataInitializer;
import intercom_bot.messages.message_handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;

/**
 * Main bot bean.
 */
@Component("intercomBot")
@Scope("singleton")
public class IntercomBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String name;

    @Value("${bot.token}")
    private String token;

    private MessageHandler messageHandler;

    private DataInitializer dataInitializer;

    @Autowired
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Autowired
    public void setDataInitializer(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @PostConstruct
    private void initData() {
        dataInitializer.initIntercomData();
    }

    /**
     * Handles all messages for all users and starts processing them in dfferent threads.
     * @param update message.
     */
    @Override
    public void onUpdateReceived(Update update) {
        new Thread(() -> messageHandler.handle(this, update)).start();
    }
}
