package me.hypews.coldtimer.commands;

import dev.negativekb.api.plugin.command.Command;
import dev.negativekb.api.plugin.command.annotation.CommandInfo;
import me.hypews.coldtimer.ColdTimer;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.Locale;
import me.hypews.coldtimer.core.managers.Member;
import me.hypews.coldtimer.core.runnable.FreezeCheckRunnable;
import me.hypews.coldtimer.core.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@CommandInfo(name = "ct", description = "Toggle a player's freeze effect",
        permission = "ct.admin", args = {"player"}, aliases = {"coldtimer"})
public class CommandCt extends Command {
    private final MemberManager memberManager;

    public CommandCt() {
        this.memberManager = API.getInstance().getMemberManager();
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (!getPlayer(args[0]).isPresent()) {
            Locale.NO_PLAYER.send(sender);
            return;
        }
        assert target != null;

        toggle(target.getUniqueId().toString());
    }

    private void toggle(String uuid) {
        FileConfiguration data = new ConfigUtils("data").getConfig();
        List<String> toggled = data.getStringList("toggled");
        Member member = memberManager.getPlayer(UUID.fromString(uuid));
        boolean status;
        if (!toggled.contains(uuid)) {
            toggled.add(uuid);
            status = true;
            member.setFrozen(true);
        } else {
            toggled.remove(uuid);
            status = false;
            member.setFrozen(false);
        }
        new ConfigUtils("data").save();

        String s = (status ? "&bON" : "&4OFF");
        Locale.FREEZE_EFFECT_TOGGLED.replace("%1", Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName()).replace("%2", s);
        if (status)
            new FreezeCheckRunnable(Bukkit.getPlayer(uuid)).runTaskTimerAsynchronously(ColdTimer.getInstance(), 20L * 3, 20L);
    }
}
