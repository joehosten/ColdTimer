package me.hypews.coldtimer.core.managers;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

@Data
public class Member {
    private final UUID uuid;
    private boolean isFrozen;
    private boolean toggleFrozen;
    private String name;

    public Member(UUID uuid) {
        this.uuid = uuid;
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public String getName() {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    public void setFrozenToggle(boolean t) {
        setToggleFrozen(t);
    }

}
