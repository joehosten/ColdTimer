package me.hypews.coldtimer.commands;

import dev.negativekb.api.plugin.command.Command;
import dev.negativekb.api.plugin.command.annotation.CommandInfo;
import me.hypews.coldtimer.ColdTimer;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

@CommandInfo(name = "ct", description = "Toggle a player's freeze effect", permission = "ct.admin", aliases = {"coldtimer"})
public class CommandCt extends Command {
    private final MemberManager memberManager;
    private final int waitTime;

    public CommandCt() {
        this.memberManager = API.getInstance().getMemberManager();
        this.waitTime = ColdTimer.getInstance().getConfig().getInt("waitTime");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0 || args.length > 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /ct <player> (on/off/check)");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (!getPlayer(args[0]).isPresent()) {
            Locale.NO_PLAYER.send(sender);
            return;
        }
        if (args.length == 1) {
            assert target != null;
            toggle(target.getUniqueId().toString(), sender);
        }
        switch (args[1].toLowerCase()) {
            case "on":
                assert target != null;
                toggle(target.getUniqueId().toString(), sender, true);
                return;
            case "off":
                assert target != null;
                toggle(target.getUniqueId().toString(), sender, false);
                return;
            case "check":
                assert target != null;
                boolean toggle;
                toggle = memberManager.isFrozenToggled(target.getUniqueId()).isPresent();
                String toSend = toggle ? "&aON" : "&4OFF";
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a%1's freeze toggle is &e%2".replace("%1", target.getName()).replace("%2", toSend)));
                return;
            default:
                sender.sendMessage(ChatColor.RED + "Usage: /ct <player> (on/off)");
        }
    }

    private void toggle(String uuid, CommandSender sender) {
        if (!memberManager.isFrozenToggled(UUID.fromString(uuid)).isPresent()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    memberManager.load(UUID.fromString(uuid));
                }
            }.runTaskLaterAsynchronously(ColdTimer.getInstance(), 20L * waitTime);

        } else {
            memberManager.unload(UUID.fromString(uuid));
        }

        Locale.FREEZE_EFFECT_TOGGLED.replace("%1", Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(uuid))).getName()).send(sender);
    }

    private void toggle(String uuid, CommandSender sender, boolean toggle) {
        if (toggle && !memberManager.isFrozenToggled(UUID.fromString(uuid)).isPresent()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    memberManager.load(UUID.fromString(uuid));
                }
            }.runTaskLaterAsynchronously(ColdTimer.getInstance(), 20L * waitTime);

        } else {
            memberManager.unload(UUID.fromString(uuid));
        }

        Locale.FREEZE_EFFECT_TOGGLED.replace("%1", Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(uuid))).getName()).send(sender);
    }
}