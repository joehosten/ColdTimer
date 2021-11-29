package me.hypews.coldtimer;

import dev.negativekb.api.plugin.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import me.hypews.coldtimer.api.APIProvider;
import me.hypews.coldtimer.commands.CommandCt;
import me.hypews.coldtimer.core.Locale;
import me.hypews.coldtimer.core.runnable.FreezeCheckRunnable;

public final class ColdTimer extends BasePlugin {

    @Getter
    @Setter
    private static ColdTimer instance;

    @Override
    public void onEnable() {
        super.onEnable();
        setInstance(this);
        new APIProvider();

        // Register commands
        registerCommands(new CommandCt());

        // Loads configs
        loadFiles(this, "settings.yml", "data.yml");
        Locale.init(this);

        // Metrics
        int pluginId = 13450;
        new Metrics(this, pluginId);

        // Runnable
        new FreezeCheckRunnable().runTaskTimerAsynchronously(this, 0, 0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
