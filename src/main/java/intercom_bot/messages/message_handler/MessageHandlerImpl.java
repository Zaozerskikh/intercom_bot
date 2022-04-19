package intercom_bot.messages.message_handler;

import intercom_bot.data.service.IntercomService;
import intercom_bot.messages.message_parser.MessageParser;
import intercom_bot.messages.message_sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Class that handles messages from users and processes them.
 */
@Component
@Scope("prototype")
@EnableAspectJAutoProxy
public class MessageHandlerImpl implements MessageHandler {
    private MessageSender messageSender;
    private MessageParser messageParser;
    private IntercomService intercomService;

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Autowired
    public void setIntercomService(IntercomService intercomService) {
        this.intercomService = intercomService;
    }

    @Autowired
    public void setMessageParser(MessageParser messageParser) {
        this.messageParser = messageParser;
    }

    /**
     * New message processing.
     * @param bot bot object.
     * @param update message.
     */
    @Override
    public void handle(TelegramLongPollingBot bot, Update update) {
        long chatID = update.getMessage().getChatId();
        String userNickname = update.getMessage().getChat().getUserName();
        try {
            messageSender.sendList(bot, chatID, intercomService.findAddress(messageParser.parseMessage(update)));
        } catch (RuntimeException e) {
            messageSender.sendText(bot, chatID, e.getMessage());
        }
    }
}
