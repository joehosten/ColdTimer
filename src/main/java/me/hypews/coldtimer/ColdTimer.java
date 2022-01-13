package me.hypews.coldtimer;

import dev.negativekb.api.plugin.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.APIProvider;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.commands.CommandCt;
import me.hypews.coldtimer.core.Locale;
import me.hypews.coldtimer.core.runnable.FreezeCheckRunnable;
import me.hypews.coldtimer.listeners.DimensionChangeEvent;

import java.util.List;
import java.util.UUID;

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
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Locale.init(this);

        // Metrics
        int pluginId = 13450;
        new Metrics(this, pluginId);

        // Runnable
        new FreezeCheckRunnable().runTaskTimerAsynchronously(this, 20, 0);
        cachePlayers();


        registerListeners(new DimensionChangeEvent());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void cachePlayers() {
        MemberManager memberManager = API.getInstance().getMemberManager();
        List<String> members = getConfig().getStringList("toggled");
        if (members.isEmpty()) return;
        members.forEach(member -> memberManager.load(UUID.fromString(member)));
    }
}
