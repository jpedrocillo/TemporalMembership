package com.temporal.membership.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivationActivity {

    @ActivityMethod
    void setActivateAccount(String registrationNo);

}
