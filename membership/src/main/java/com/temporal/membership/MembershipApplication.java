package com.temporal.membership;

import com.temporal.membership.activities.AccountActivationActivity;
import com.temporal.membership.activities.MembershipActivity;
import com.temporal.membership.workflows.WorkflowAccountActivation;
import com.temporal.membership.workflows.WorkflowAccountActivationImpl;
import com.temporal.membership.workflows.WorkflowMembership;
import com.temporal.membership.workflows.WorkflowMembershipImpl;
import io.temporal.activity.Activity;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MembershipApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext appContext = SpringApplication.run(MembershipApplication.class, args);

		WorkerFactory factory = appContext.getBean(WorkerFactory.class);

		//create worker for membership WF
		Worker workerMembership = factory.newWorker(WorkflowMembership.QUEUE_NAME); //WF Name
		workerMembership.registerWorkflowImplementationTypes(WorkflowMembershipImpl.class); //WF will follow base on defined @WorkflowMethod on class
		MembershipActivity membershipActivity = appContext.getBean(MembershipActivity.class);
		//Define the activities need to be done by worker
		workerMembership.registerActivitiesImplementations(membershipActivity);

		//create worker for membership WF
		Worker workerAccount = factory.newWorker(WorkflowAccountActivation.QUEUE_NAME); //WF Name
		workerAccount.registerWorkflowImplementationTypes(WorkflowAccountActivationImpl.class); //WF will follow base on defined @WorkflowMethod on class
		AccountActivationActivity accountActivationActivity = appContext.getBean(AccountActivationActivity.class);
		//Define the activities need to be done by worker
		workerAccount.registerActivitiesImplementations(accountActivationActivity);

		factory.start();
	}

}
