package me.hypews.coldtimer.core.managers;

import lombok.Data;

import java.util.UUID;

@Data
public class Member {
    private final UUID uuid;
    private String name;

    public Member(UUID uuid) {
        this.uuid = uuid;
    }
}
