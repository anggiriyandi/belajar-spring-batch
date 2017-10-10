/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.listener;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import javax.websocket.OnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */
@Component
public class ItemReaderListener {
    Logger logger = LoggerFactory.getLogger(ItemReaderListener.class);

    @BeforeRead
    public void beforeRead() {
        logger.info("INTERCEPTOR SEBELUM BACA FILE");
    }

    @AfterRead
    public void afterRead(Peserta p) {
        logger.info("INTERCEPTOR SETELEAH BACA FILE : {}",p);
    }
    
    @OnReadError
    public void onReadError(Exception ex) {
        logger.error("INTERCEPTOR KETIKA ADA YANG ERROR : {}",ex.getMessage());
    }

}
