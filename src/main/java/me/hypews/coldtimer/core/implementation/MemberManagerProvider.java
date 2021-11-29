package me.hypews.coldtimer.core.implementation;

import me.hypews.coldtimer.api.MemberManager;
import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Objects;
import java.util.UUID;

public class MemberManagerProvider implements MemberManager {

    @Override
    public Member getPlayer(UUID uuid) {
        return new Member(uuid);
    }

    @Override
    public boolean isFrozenToggled(UUID uuid) {
        Member member = new Member(uuid);
        return member.isToggleFrozen();
    }

    @Override
    public void setFrozen(UUID uuid) {
        Member member = new Member(uuid);
        member.setFrozen(true);
    }

    @Override
    public World.Environment getDemension(UUID uuid) {
        Member member = new Member(uuid);
        return Objects.requireNonNull(Bukkit.getPlayer(member.getUuid())).getWorld().getEnvironment();
    }
}
