package me.hypews.coldtimer;

import dev.negativekb.api.plugin.BasePlugin;
import me.hypews.coldtimer.commands.CommandCt;

public final class ColdTimer extends BasePlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        // Register commands
        registerCommands(new CommandCt());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
