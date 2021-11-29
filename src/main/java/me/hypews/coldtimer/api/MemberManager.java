package me.hypews.coldtimer.api;

import me.hypews.coldtimer.core.managers.Member;
import org.bukkit.World;

import java.util.List;
import java.util.Optional;
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
    Optional<Member> isFrozenToggled(UUID uuid);


    /**
     * @param uuid uuid of player
     * @return The dimension the player is in
     */
    World.Environment getDimension(UUID uuid);

    void load(UUID uuid);

    void unload(UUID uuid);

    List getMembers();
}
