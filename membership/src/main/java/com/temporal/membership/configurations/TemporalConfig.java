package com.temporal.membership.configurations;

import com.temporal.membership.activities.AccountActivationActivity;
import com.temporal.membership.activities.AccountActivationActivityImpl;
import com.temporal.membership.activities.MembershipActivity;
import com.temporal.membership.activities.MembershipActivityImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    private String temporalServiceAddress = "127.0.0.1:7233";

    private String temporalNameSpace= "default";

    final private WorkflowClientOptions options = WorkflowClientOptions.newBuilder()
            .setNamespace(temporalNameSpace)
            .build();

    @Bean
    public WorkflowServiceStubs workflowServiceStubs(){
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs service){

        //return WorkflowClient.newInstance(service, options);
        return WorkflowClient.newInstance(service);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient client){
        return WorkerFactory.newInstance(client);
    }

    @Bean
    public MembershipActivityImpl MembershipActivity() {
        return new MembershipActivityImpl(); // Constructor Injection
    }

    @Bean
    public AccountActivationActivityImpl AccountActivity() {
        return new AccountActivationActivityImpl(); // Constructor Injection
    }
}
