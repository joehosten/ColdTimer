package me.hypews.coldtimer;

import dev.negativekb.api.plugin.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import me.hypews.coldtimer.api.APIProvider;
import me.hypews.coldtimer.commands.CommandCt;
import me.hypews.coldtimer.core.Locale;

public final class ColdTimer extends BasePlugin {

    @Getter
    @Setter
    private static ColdTimer instance;

    @Override
    public void onEnable() {
        super.onEnable();

        // Register commands
        registerCommands(new CommandCt());
        new APIProvider();

        // Loads configs
        loadFiles(this, "settings.yml");
        Locale.init(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
