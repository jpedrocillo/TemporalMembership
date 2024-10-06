package com.temporal.membership.workflows;

import com.temporal.membership.model.MembershipDto;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowMembership {

    public static final String QUEUE_NAME = "Membership_Registration";
    public static final String WF_ID_NAME = "Membership_Registration_";

    @WorkflowMethod
    void startRegistrationSignal(MembershipDto inputTransaction);

    @SignalMethod
    void verificationSignal();

    @SignalMethod
    void createMemberAccountSignal();

    @SignalMethod
    void completeMembershipSignal();

}
