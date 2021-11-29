package me.hypews.coldtimer.core.runnable;

import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FreezeCheckRunnable extends BukkitRunnable {

    private final List<Member> members;
    private final MemberManager memberManager;

    public FreezeCheckRunnable() {
        this.memberManager = API.getInstance().getMemberManager();
        this.members = memberManager.getMembers();
    }

    @Override
    public void run() {
        members.forEach(member -> {
            Player p = Bukkit.getPlayer(member.getUuid());
            assert p != null;
            if (memberManager.getDimension(p.getUniqueId()) == Environment.NORMAL) {
                p.setFreezeTicks(p.getMaxFreezeTicks());
            }
        });
    }
}
