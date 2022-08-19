package theoneamin.com.tutorial.batch;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import theoneamin.com.tutorial.batch.listeners.OrderJobListener;
import theoneamin.com.tutorial.batch.listeners.OrderStepListener;
import theoneamin.com.tutorial.batch.processor.OrderProcessor;

import javax.sql.DataSource;
import java.util.Random;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    DataSource dataSource;

    private final String JOB_NAME = "batchReaderJob";
    private final String STEP_NAME = "batchReaderStep";

    Random random = new Random();
    int randomInt = random.nextInt();


    @Bean
    public Job batchReaderJob() {
        return jobBuilderFactory.get(JOB_NAME+randomInt)
                .start(batchReaderStep())
                .listener(new OrderJobListener())
                .build();
    }

    @Bean
    public Step batchReaderStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Order, Order>chunk(100)
                .reader(orderItemReader())
                .processor(orderProcessor())
                .writer(orderWriter())
                .listener(new OrderStepListener())
                .build();
    }

    @Bean
    public ItemProcessor<Order, Order> orderProcessor() {
        return new OrderProcessor();
    }

    @Bean
    public ItemWriter<Order> orderWriter() {
        return new OrdersWriter();
    }

    @Bean
    public ItemReader<Order> orderItemReader() {
        String sql = "SELECT * FROM SHIPPED_ORDER";

        return new JdbcCursorItemReaderBuilder<Order>()
                .name("orderItemReader")
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new OrdersMapper())
                .build();
    }
}
