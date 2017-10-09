/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.tasklet;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */
@Component
public class DeletePesertaCsvTasklet implements Tasklet{
    Logger logger = LoggerFactory.getLogger(DeletePesertaCsvTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        File file = new ClassPathResource("data-peserta.csv").getFile();
        if(file.delete()){
            logger.info("FILE {} DELETED !!",file.getName());
        }else{
            logger.error("UNABLE TO DELETE FILE !!");
        }
        return RepeatStatus.FINISHED;
    }   
}
