package me.hypews.coldtimer.core.runnable;

import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Optional;

public class FreezeCheckRunnable extends BukkitRunnable {

    private final List<Member> members;
    private final MemberManager memberManager;


    public FreezeCheckRunnable() {
        this.memberManager = API.getInstance().getMemberManager();
        this.members = memberManager.getMembers();
    }

    @Override
    public void run() {
        if (members == null) {
            return;
        }
        members.forEach(member -> {
            Optional<Player> p = Optional.ofNullable(Bukkit.getPlayer(member.getUuid()));
            if (!p.isPresent() || !members.contains(member)) return;
//            if (memberManager.getDimension(p.get().getUniqueId()) == Environment.NORMAL) {
            p.get().setFreezeTicks(p.get().getMaxFreezeTicks());
//            }
        });
    }
}
