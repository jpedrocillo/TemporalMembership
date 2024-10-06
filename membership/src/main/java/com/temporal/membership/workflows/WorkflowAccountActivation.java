package com.temporal.membership.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowAccountActivation {
    public static final String QUEUE_NAME = "Account_Activation";
    public static final String WF_ID_NAME = "Account_Activation_";

    @WorkflowMethod
    void activate(String registrationNo);
}
