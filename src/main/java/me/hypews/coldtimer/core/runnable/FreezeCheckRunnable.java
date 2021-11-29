package me.hypews.coldtimer.core.runnable;

import me.hypews.coldtimer.ColdTimer;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Optional;

public class FreezeCheckRunnable extends BukkitRunnable implements Listener {

    private final List<Member> members;
    private final MemberManager memberManager;
    private int waitTime;

    public FreezeCheckRunnable() {
        this.memberManager = API.getInstance().getMemberManager();
        this.members = memberManager.getMembers();
        this.waitTime = ColdTimer.getInstance().getConfig().getInt("waittime");
    }

    @Override
    public void run() {
        if (members == null) {
            return;
        }
        members.forEach(member -> {
            Optional<Player> p = Optional.ofNullable(Bukkit.getPlayer(member.getUuid()));
            if (!p.isPresent() || !members.contains(member)) return;
            if (memberManager.getDimension(p.get().getUniqueId()) == Environment.NORMAL) {
                new BukkitRunnable() {
                    @Override
                    public void run() { // Waits x time before executing the freeze effect
                        p.get().setFreezeTicks(p.get().getMaxFreezeTicks());
                    }
                }.runTaskLaterAsynchronously(ColdTimer.getInstance(), 20L * waitTime);
            }
        });
    }
}
