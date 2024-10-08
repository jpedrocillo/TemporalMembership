package com.temporal.membership.activities;

import io.temporal.activity.Activity;

public class MembershipActivityImpl implements MembershipActivity {
    @Override
    public void setStartRegistration() {
        boolean activityShouldSucceed = true;

        if (!activityShouldSucceed) {
            System.out.println("[Failed] Membership Registration failed.");
            //System.out.flush();
            throw Activity.wrap(new RuntimeException("Simulated Activity error on setStartRegistration."));
        }
        System.out.println("**** Membership Registration is placed.");
    }

    @Override
    public void setVerification() {
        boolean activityShouldSucceed = true;

        if (!activityShouldSucceed) {
            System.out.println("[Failed] Membership Verification failed.");
            //System.out.flush();
            throw Activity.wrap(new RuntimeException("Simulated Activity error on setVerification."));
        }
        System.out.println("**** Membership Registration is verified.");
    }

    @Override
    public void setMemberAccount() {
        boolean activityShouldSucceed = true;

        if (!activityShouldSucceed) {
            System.out.println("[Failed] Membership Account Creation failed.");
            //System.out.flush();
            throw Activity.wrap(new RuntimeException("Simulated Activity error on setMemberAccount."));
        }
        System.out.println("**** Account created for the Membership Registration.");

    }

    @Override
    public void setCompleteMembership() {
        boolean activityShouldSucceed = true;

        if (!activityShouldSucceed) {
            System.out.println("[Failed] Notify Membership Account Complete process failed.");
            //System.out.flush();
            throw Activity.wrap(new RuntimeException("Simulated Activity error on setCompleteMembership."));
        }
        System.out.println("**** Membership Registration complete, sending email now to the member.");
    }

}
