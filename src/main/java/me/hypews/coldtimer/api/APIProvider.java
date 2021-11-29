package me.hypews.coldtimer.api;

public class APIProvider extends API {
    private MemberManager memberManager;

    @Override
    public MemberManager getMemberManager() {
        return memberManager;
    }
}
