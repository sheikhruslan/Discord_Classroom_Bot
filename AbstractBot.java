package bot;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class AbstractBot extends ListenerAdapter {
    public abstract void actionPerform();
}
