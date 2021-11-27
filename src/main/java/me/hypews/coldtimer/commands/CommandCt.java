package me.hypews.coldtimer.commands;

import dev.negativekb.api.plugin.command.Command;
import dev.negativekb.api.plugin.command.annotation.CommandInfo;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "ct", description = "Toggle a player's freeze effect",
        permission = "ct.admin", args = {"player"}, aliases = {"coldtimer"})
public class CommandCt extends Command {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        //  TODO - CREATE TOGGLE METHOD
        //  TODO - CREATE RUNNABLE
        //  TODO - CREATE EFFECT
        //  TODO - CREATE CHECKER FOR DIMENSION CHANGE
    }
}
