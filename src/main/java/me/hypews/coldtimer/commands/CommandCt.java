package me.hypews.coldtimer.commands;

import dev.negativekb.api.plugin.command.Command;
import dev.negativekb.api.plugin.command.annotation.CommandInfo;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "That player is not online or does not exist.");
            return;
        }
        Member member = memberManager.getPlayer(target.getUniqueId());
        member.setFrozen(true);
    }
}
