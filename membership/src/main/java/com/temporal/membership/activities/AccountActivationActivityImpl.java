package com.temporal.membership.activities;

import io.temporal.activity.Activity;

public class AccountActivationActivityImpl implements AccountActivationActivity{
    @Override
    public void setActivateAccount(String registrationNo) {
        boolean activityShouldSucceed = true;

        if (!activityShouldSucceed) {
            System.out.println("[Failed] Account Activation failed.");
            //System.out.flush();
            throw Activity.wrap(new RuntimeException("Simulated Activity error on setActivateAccount"));
        }
        System.out.println("**** Account "+registrationNo+" is activated.");
    }
}
