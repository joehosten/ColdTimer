package me.hypews.coldtimer.listeners;

import me.hypews.coldtimer.ColdTimer;
import me.hypews.coldtimer.api.API;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class DimensionChangeEvent implements Listener {

    private final List<Member> members;
    private final MemberManager memberManager;
    private final int waitTime;
    private ArrayList<String> inNetherWithToggle;

    public DimensionChangeEvent() {
        this.memberManager = API.getInstance().getMemberManager();
        this.members = memberManager.getMembers();
        this.inNetherWithToggle = new ArrayList<>();
        this.waitTime = ColdTimer.getInstance().getConfig().getInt("waitTime");
    }

    @EventHandler
    public void onDimensionChange(PlayerChangedWorldEvent e) {
        World.Environment fromEnv = e.getFrom().getEnvironment();
        World.Environment toEnv = e.getPlayer().getWorld().getEnvironment();
        Member member = new Member(e.getPlayer().getUniqueId());
        member.setName(e.getPlayer().getName());
        if (fromEnv == World.Environment.NORMAL && (toEnv == World.Environment.NETHER || toEnv == World.Environment.THE_END)) {
            if (!members.contains(member)) return;
            memberManager.unload(e.getPlayer().getUniqueId());
            inNetherWithToggle.add(e.getPlayer().getUniqueId().toString());
        } else if ((fromEnv == World.Environment.THE_END || fromEnv == World.Environment.NETHER) && toEnv == World.Environment.NORMAL && inNetherWithToggle.contains(e.getPlayer().getUniqueId().toString())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    memberManager.load(e.getPlayer().getUniqueId());
                }
            }.runTaskLaterAsynchronously(ColdTimer.getInstance(), 20L * waitTime);

            inNetherWithToggle.remove(e.getPlayer().getUniqueId().toString());
        }
    }

}
