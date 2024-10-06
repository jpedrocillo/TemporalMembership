package com.temporal.membership.services;

import com.temporal.membership.activities.MembershipActivity;
import com.temporal.membership.model.MembershipDto;
import com.temporal.membership.workflows.WorkflowMembership;
import com.temporal.membership.workflows.WorkflowMembershipImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
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
        WorkflowMembership workflow = createWorkFlowConnection(inputTransaction.getRegistrationNo());
        WorkflowClient.start(workflow::startRegistrationSignal, inputTransaction);

    }

    @Override
    public void verify(String registrationNo) {
        //look and update a workflow request
        WorkflowMembership workflow = workflowClient.newWorkflowStub(WorkflowMembership.class, WorkflowMembership.WF_ID_NAME + registrationNo);
        workflow.verificationSignal();
    }

    @Override
    public void createAccount(String registrationNo) {
        //look and update a workflow request
        WorkflowMembership workflow = workflowClient.newWorkflowStub(WorkflowMembership.class, WorkflowMembership.WF_ID_NAME + registrationNo);
        workflow.createMemberAccountSignal();
    }

    @Override
    public void complete(String registrationNo) {
        //look and update a workflow request
        WorkflowMembership workflow = workflowClient.newWorkflowStub(WorkflowMembership.class, WorkflowMembership.WF_ID_NAME + registrationNo);
        workflow.completeMembershipSignal();
    }

    //Create Workflow Connection
    public WorkflowMembership createWorkFlowConnection(String id) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(WorkflowMembership.QUEUE_NAME)
                .setWorkflowId(WorkflowMembership.WF_ID_NAME + id)
                .setWorkflowTaskTimeout(Duration.ofSeconds(10))
                .setWorkflowRunTimeout(Duration.ofSeconds(10))
                .build();

        return workflowClient.newWorkflowStub(WorkflowMembership.class, options);
    }
}
