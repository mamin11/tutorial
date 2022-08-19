package theoneamin.com.tutorial.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class OrderStepListener implements StepExecutionListener{

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Do something before the step starts");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Do something after the step is done");
        return null;
    }
}
