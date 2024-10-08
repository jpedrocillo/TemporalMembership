package com.temporal.membership.services;

import com.temporal.membership.model.MembershipDto;
import com.temporal.membership.workflows.MembershipWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class MembershipServiceImpl implements MembershipService{

    @Autowired
    WorkflowServiceStubs workflowServiceStubs;

    @Autowired
    WorkflowClient workflowClient;

    @Override
    public void register(MembershipDto inputTransaction) {
        // create a new work flow request using createWorkFlowConnection()
        MembershipWorkflow workflow = createWorkFlowConnection(inputTransaction.getRegistrationNo());
        WorkflowClient.start(workflow::startRegistrationSignal, inputTransaction);

    }

    @Override
    public void verify(String registrationNo) {
        //look and update a workflow request
        MembershipWorkflow workflow = workflowClient.newWorkflowStub(MembershipWorkflow.class, MembershipWorkflow.WF_ID_NAME + registrationNo);
        workflow.verificationSignal();
    }

    @Override
    public void createAccount(String registrationNo) {
        //look and update a workflow request
        MembershipWorkflow workflow = workflowClient.newWorkflowStub(MembershipWorkflow.class, MembershipWorkflow.WF_ID_NAME + registrationNo);
        workflow.createMemberAccountSignal();
    }

    @Override
    public void complete(String registrationNo) {
        //look and update a workflow request
        MembershipWorkflow workflow = workflowClient.newWorkflowStub(MembershipWorkflow.class, MembershipWorkflow.WF_ID_NAME + registrationNo);
        workflow.completeMembershipSignal();
    }

    //Create Workflow Connection
    public MembershipWorkflow createWorkFlowConnection(String id) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(MembershipWorkflow.QUEUE_NAME)
                .setWorkflowId(MembershipWorkflow.WF_ID_NAME + id)
                .setWorkflowTaskTimeout(Duration.ofSeconds(120))
                .setWorkflowRunTimeout(Duration.ofSeconds(180))
                .build();

        return workflowClient.newWorkflowStub(MembershipWorkflow.class, options);
    }
}
