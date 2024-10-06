package com.temporal.membership.workflows;

import com.temporal.membership.activities.MembershipActivity;
import com.temporal.membership.model.MembershipDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class WorkflowMembershipImpl implements WorkflowMembership {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(10))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(2).build();

    private final ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .setHeartbeatTimeout(Duration.ofSeconds(2))
            .setScheduleToCloseTimeout(Duration.ofSeconds(5))
            .setRetryOptions(retryoptions).build();

    //set the retry option on the activity
    private final MembershipActivity activity = Workflow.newActivityStub(MembershipActivity.class, options);

    public boolean isVerified= false;
    public boolean isAccountCreated= false;
    public boolean isAccountRegistrationCompleted= false;

    @Override
    public void startRegistrationSignal(MembershipDto inputTransaction) {

        activity.setStartRegistration();
        System.out.println("[Next Activity] Waiting to verify your membership registration.");
        Workflow.await(() -> isVerified);

        System.out.println("[Next Activity] Waiting to finish the process of creating membership Account.");
        Workflow.await(() -> isAccountCreated);

        System.out.println("[Next Activity] Waiting to send new Account details to client.");
        Workflow.await(() -> isAccountRegistrationCompleted);
    }

    @Override
    public void verificationSignal() {
        activity.setVerification();
        this.isVerified = true;
    }

    @Override
    public void createMemberAccountSignal() {

        if(isVerified) {
            activity.setMemberAccount();
            this.isAccountCreated = true;
        }else{
            System.out.println("[Failed] Cannot Skip other activity");
        }

    }

    @Override
    public void completeMembershipSignal() {

        if(isVerified && isAccountCreated) {
            activity.setCompleteMembership();
            this.isAccountRegistrationCompleted = true;
        }else{
            System.out.println("[Failed] Cannot Skip other activity");
        }

    }
}
