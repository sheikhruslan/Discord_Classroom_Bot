package tools;

import java.util.List;
import java.util.ArrayList;

/**
 * This class is to store the command sent by the user.
 * It shares the same similarity as a Message object except
 * that a command may also has options.
 * 
 * Each option is modeled as a TextPair object. A command
 * may have no option, one option, or multiple options.
 * 
 * This class support the following methods:
 * - addOption (to add an option)
 * - getOption (to get the value of an option)
 * - getOptions (to get all options)
 */

public class Command extends Message {
    //TODO - add data members and methods
    private List<TextPair> options;

    public Command(String name, String id, String content, boolean isPrivate) {
        super(name, id, content, isPrivate);
        options = new ArrayList<>();
    }

    public void addOption(TextPair option) {
        options.add(option);
    }

    public String getOption(int i) {
        // before getting an option you need to add first?
        return options.get(i).getValue();
    }

    public List<TextPair> getOptions() {
        return options;
    }

    public void addOption(String name, String value) {
        TextPair option = new TextPair(name, value);
        options.add(option);
    }

    public String getSenderID() {
        // you need a senderID
        return super.getId();
    }
}
