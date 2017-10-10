/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.listener;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */

@Component
public class CustomSkipListener {
    Logger logger = LoggerFactory.getLogger(CustomSkipListener.class);
    
    @OnSkipInRead
    public void onSkipInRead(Throwable t){
        logger.error("INTERPT ON SKIP IN READER : {}",t.getMessage());
    }
    
    @OnSkipInWrite
    public void onSkipWrite(Peserta p, Throwable t){
        logger.error("OBJECT YANG ERROR KETIKA DI SAVE : {}",p);
    }
    
    @OnSkipInProcess
    public void onSkipInProcess(Peserta p, Throwable t){
        logger.error("OBJECT YANG ERROR KETIKA DI ROCESS : {}", p);
    }
}
