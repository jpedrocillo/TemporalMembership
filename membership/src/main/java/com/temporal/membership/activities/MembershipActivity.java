package com.temporal.membership.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface MembershipActivity {

    @ActivityMethod
    void setStartRegistration();

    @ActivityMethod
    void setVerification();

    @ActivityMethod
    void setMemberAccount();

    @ActivityMethod
    void setCompleteMembership();

}
