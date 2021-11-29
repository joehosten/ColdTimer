package me.hypews.coldtimer.api;

import me.hypews.coldtimer.core.implementation.MemberManagerProvider;

public class APIProvider extends API {
    private final MemberManager memberManager;

    public APIProvider() {
        setInstance(this);

        memberManager = new MemberManagerProvider();
    }

    @Override
    public MemberManager getMemberManager() {
        return memberManager;
    }
}
