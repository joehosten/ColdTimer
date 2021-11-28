package me.hypews.coldtimer;

import dev.negativekb.api.plugin.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import me.hypews.coldtimer.commands.CommandCt;

public final class ColdTimer extends BasePlugin {

    @Getter
    @Setter
    private static ColdTimer instance;

    @Override
    public void onEnable() {
        super.onEnable();

        // Register commands
        registerCommands(new CommandCt());

        // Loads configs
        loadFiles(this, "settings.yml");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
