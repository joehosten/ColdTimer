package me.hypews.coldtimer.api;

import lombok.Getter;
import lombok.Setter;

public abstract class API {

    @Getter
    @Setter
    private static API instance;

    public abstract MemberManager getMemberManager();


}
