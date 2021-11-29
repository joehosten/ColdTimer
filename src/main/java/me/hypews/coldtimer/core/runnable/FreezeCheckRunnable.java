package me.hypews.coldtimer.core.runnable;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FreezeCheckRunnable extends BukkitRunnable {

    private final Player p;


    public FreezeCheckRunnable(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        p.setFreezeTicks(p.getMaxFreezeTicks());
    }
}
