package com.temporal.membership.workflows;

import com.temporal.membership.activities.AccountActivationActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class WorkflowAccountActivationImpl implements  WorkflowAccountActivation{

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(10))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(2).build();

    private final ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(15))
            .setHeartbeatTimeout(Duration.ofSeconds(2))
            .setRetryOptions(retryoptions).build();

    //set the retry option on the activity
    private final AccountActivationActivity activity = Workflow.newActivityStub(AccountActivationActivity.class, options);

    @Override
    public void activate(String registrationNo) {
        activity.setActivateAccount(registrationNo);
    }
}
