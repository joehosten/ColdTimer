package me.hypews.coldtimer.core.implementation;

import me.hypews.coldtimer.ColdTimer;
import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MemberManagerProvider implements MemberManager {

    private final ArrayList<Member> members = new ArrayList<>();

    public MemberManagerProvider() {
        new MemberSaveTask().runTaskTimerAsynchronously(ColdTimer.getInstance(), 0, 20L * 30L);
    }

    @Override
    public Member getPlayer(UUID uuid) {
        return new Member(uuid);
    }

    @Override
    public Optional<Member> isFrozenToggled(UUID uuid) {
        return members.stream().filter(member -> member.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public World.Environment getDimension(UUID uuid) {
        return Objects.requireNonNull(Bukkit.getPlayer(uuid)).getWorld().getEnvironment();
    }

    @Override
    public void load(UUID uuid) {
        if (!isFrozenToggled(uuid).isPresent()) {
            Member member = new Member(uuid);
            member.setName(Bukkit.getOfflinePlayer(uuid).getName());
            members.add(member);
        }
    }

    @Override
    public void unload(UUID uuid) {
        isFrozenToggled(uuid).ifPresent(members::remove);
    }

    @Override
    public List<Member> getMembers() {
        return members;
    }


    private class MemberSaveTask extends BukkitRunnable {

        @Override
        public void run() {
            List<String> toSave = new ArrayList<>();
            members.forEach(member -> {
                toSave.add(member.getUuid().toString());
            });
            ColdTimer.getInstance().getConfig().set("toggled", toSave);
            ColdTimer.getInstance().saveConfig();
        }
    }
}
