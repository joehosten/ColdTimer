package me.hypews.coldtimer.api;

import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.World;

import java.util.UUID;

public interface MemberManager {

    /**
     * @param uuid uuid of player
     * @return Member instance
     */
    Member getPlayer(UUID uuid);

    /**
     * @param uuid uuid of player
     * @return If the player has been set to frozen in the config
     */
    boolean isFrozenToggled(UUID uuid);

    /**
     * @param uuid uuid of player
     */
    void setFrozen(UUID uuid);

    /**
     * @param uuid uuid of player
     * @return The dimension the player is in
     */
    World.Environment getDemension(UUID uuid);

}
