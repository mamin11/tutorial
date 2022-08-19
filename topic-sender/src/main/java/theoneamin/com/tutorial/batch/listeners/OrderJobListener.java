package theoneamin.com.tutorial.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class OrderJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
      log.debug("Do something before the job starts");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
      log.debug("Do something after the job");
    }
}
