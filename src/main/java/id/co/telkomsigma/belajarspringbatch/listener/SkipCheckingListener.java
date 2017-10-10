/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */

@Component
public class SkipCheckingListener extends StepExecutionListenerSupport{

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        
        String exitCode = stepExecution.getExitStatus().getExitCode();
        if(!exitCode.equals(ExitStatus.FAILED.getExitCode()) && 
                stepExecution.getSkipCount() > 0){
            return new ExitStatus("COMPLETED WITH ERROR");
        }else{
            return null;
        }
    }
}
