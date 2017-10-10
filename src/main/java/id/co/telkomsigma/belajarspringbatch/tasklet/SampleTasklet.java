/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */

@Component
public class SampleTasklet implements Tasklet{

    Logger logger = LoggerFactory.getLogger(SampleTasklet.class);
    
    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        logger.info("MENJALANKAN SAMPLE TASKLET !!");
        return null;
    }
}
