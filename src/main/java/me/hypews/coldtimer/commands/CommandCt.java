package me.hypews.coldtimer.commands;

import dev.negativekb.api.plugin.command.Command;
import dev.negativekb.api.plugin.command.annotation.CommandInfo;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.Locale;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

@CommandInfo(name = "ct", description = "Toggle a player's freeze effect", permission = "ct.admin", args = {"player"}, aliases = {"coldtimer"})
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
        toggle(target.getUniqueId().toString(), sender);
    }

    private void toggle(String uuid, CommandSender sender) {
        if (!memberManager.isFrozenToggled(UUID.fromString(uuid)).isPresent()) {
            memberManager.load(UUID.fromString(uuid));
        } else {
            memberManager.unload(UUID.fromString(uuid));
            Player t = Bukkit.getPlayer(UUID.fromString(uuid));
            assert t != null;
            t.setFreezeTicks(0);
        }

        String s = (memberManager.isFrozenToggled(UUID.fromString(uuid)).isPresent() ? "&2ON" : "&4OFF");
        Locale.FREEZE_EFFECT_TOGGLED.replace("%1", Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(uuid))).getName()).replace("%2", s).send(sender);
    }
}
