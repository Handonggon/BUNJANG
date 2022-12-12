package kr.co.study.bunjang.mvc.batch.chunk;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProfileDecider implements JobExecutionDecider {

    @Value("${spring.profiles.active}")
	private String activeProfile;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        return new FlowExecutionStatus(activeProfile);
    }
}