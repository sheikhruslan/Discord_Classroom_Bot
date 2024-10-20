package bot;  // need this line to indicate that you are in a package

import tools.Message;  // need this line to import the Message class

public class PingMessageListener implements MessageListener { //this is a MessageListener, it implements this interface

    @Override    //@Override is optional, you can remove it.
    public String onMessageReceived(Message message) {   // the only method you need to implement
        if (message.getContent().equals("ping")) {       // message.getContent() get your the message sent from the user
            return "pong";
        }
        return null;
    }
}
