package bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.channel.ChannelType;

import java.util.ArrayList;
import java.util.List;

import tools.*;

public final class TextBot extends AbstractBot {

    private List <MessageListener> listeners = new ArrayList<>();

    public final void addListener (MessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void actionPerform() {
        System.out.println("Print Log: TextBot is running.");
    }
    @Override
    public final void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        Message message = new Message(event.getAuthor().getName(), event.getAuthor().getId(),
                event.getMessage().getContentRaw(), event.isFromType(ChannelType.PRIVATE));


        for (MessageListener i : listeners) {
            if (i == null)
                continue;
            String reply = i.onMessageReceived(message);
            if (reply != null) {
                event.getChannel().sendMessage(reply).queue();
            }
        }
    }
}