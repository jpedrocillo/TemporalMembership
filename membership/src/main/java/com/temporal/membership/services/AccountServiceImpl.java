package com.temporal.membership.services;

import com.temporal.membership.workflows.AccountActivationWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    WorkflowServiceStubs workflowServiceStubs;

    @Autowired
    WorkflowClient workflowClient;

    @Override
    public void activate(String registrationNo) {

            // create a new work flow request using createWorkFlowConnection()
            AccountActivationWorkflow workflow = createWorkFlowConnection(registrationNo);
            WorkflowClient.start(workflow::activate, registrationNo);
    }

    //Create Workflow Connection
    public AccountActivationWorkflow createWorkFlowConnection(String id) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(AccountActivationWorkflow.QUEUE_NAME)
                .setWorkflowId(AccountActivationWorkflow.WF_ID_NAME + id)
                .build();

        return workflowClient.newWorkflowStub(AccountActivationWorkflow.class, options);
    }
}
