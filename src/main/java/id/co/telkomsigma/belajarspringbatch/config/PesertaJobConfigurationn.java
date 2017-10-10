/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.config;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import id.co.telkomsigma.belajarspringbatch.listener.CustomSkipListener;
import id.co.telkomsigma.belajarspringbatch.listener.ItemReaderListener;
import id.co.telkomsigma.belajarspringbatch.listener.SkipCheckingListener;
import id.co.telkomsigma.belajarspringbatch.mapper.PesertaMapper;
import id.co.telkomsigma.belajarspringbatch.processor.PesertaItemProcessor;
import id.co.telkomsigma.belajarspringbatch.tasklet.DeletePesertaCsvTasklet;
import id.co.telkomsigma.belajarspringbatch.writter.PesertaItemwritter;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author anggi
 */

@Configuration
public class PesertaJobConfigurationn {
   
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public PesertaItemwritter itemwritter;
    
    @Autowired
    public PesertaItemProcessor processor;
    
    @Autowired
    public DeletePesertaCsvTasklet tasklet;
    
    @Autowired
    public SkipCheckingListener skipCheckingListener;
    
    @Autowired
    public ItemReaderListener readerListener;
    
    @Autowired
    public CustomSkipListener skipListener;
    
    @Autowired
    public JobLauncher launcher;
    
    Logger logger = LoggerFactory.getLogger(PesertaJobConfigurationn.class);
    
    @Bean
    public FlatFileItemReader<Peserta> reader(){
        FlatFileItemReader<Peserta> reader = 
                new FlatFileItemReader<Peserta>();
        
        reader.setResource(new ClassPathResource("data-peserta.csv"));
        reader.setLineMapper( new DefaultLineMapper<Peserta>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"nama","alamat","tanggalLahir"});
            }});
            setFieldSetMapper(new PesertaMapper());
        }});
        
//        DefaultLineMapper<Peserta> defaultLineMapper = new DefaultLineMapper<Peserta>();
//        
//        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
//        delimitedLineTokenizer.setNames(new String[] {"nama","alamat","tanggalLahir"});
//        
//        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
//        defaultLineMapper.setFieldSetMapper(new PesertaMapper());
//        reader.setLineMapper(defaultLineMapper);
        
        return reader;
    }
    
    
    @Scheduled(cron = "*/10 * * * * *")
    public void performJob(){
        try {
            logger.info("============= job berjalan pada {} ================",new Date());
            JobParameters param = new JobParametersBuilder()
                    .addString("JobId", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            JobExecution execution = launcher.run(importDataPesertaFromCsvJob(), param);
        } catch (Exception ex) {
            logger.error("CANNOT LAUNCH JOB SCHEDULER : {}",ex.getMessage(),ex);
        }
    }
    
    @Bean
    public Job importDataPesertaFromCsvJob(){
        return jobBuilderFactory.get("importDataPesertaFromCsvJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                    .next(step2())
                .end()
                .build();
    }
    
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Peserta,Peserta> chunk(1)
                .reader(reader())
                .processor(processor)
                .writer(itemwritter)
                    .faultTolerant()
                    .skip(FlatFileParseException.class)
                    .skip(SQLDataException.class)
                    .skipLimit(2)
                    .retry(SQLDataException.class)
                    .retryLimit(3)
                .listener(skipCheckingListener)
                .listener(readerListener)
                .listener(skipListener)
                .build();
    } 
    
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet(tasklet)
                .build();
    }
}
