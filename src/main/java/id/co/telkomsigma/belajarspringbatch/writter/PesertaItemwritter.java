/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.writter;

import id.co.telkomsigma.belajarspringbatch.dao.PesertaDao;
import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import java.sql.SQLDataException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */

@Component
@StepScope
public class PesertaItemwritter implements ItemWriter<Peserta>{
    Logger logger = LoggerFactory.getLogger(PesertaItemwritter.class);
    
    @Autowired
    PesertaDao pesertaDao;
    
    @Value("#{jobParameters['JobId']}")
    private String JobId;
    
    @Override
    public void write(List<? extends Peserta> list) throws Exception {
        logger.info("PARAMETER JOB ID YANG DIKIRIM : "+JobId);
        for (Peserta peserta : list) {
            if(peserta.getNama().equalsIgnoreCase("Ari")){
                throw new SQLDataException("SENGAJA DIBUAT ERROR KETIKA SAVE !!");
            }
            
            logger.info("PESERTA YANG AKAN DI SAVE : {}",peserta.getNama());
            pesertaDao.save(peserta);
              
        }
    }

    public void setJobId(String JobId) {
        this.JobId = JobId;
    }

    public String getJobId() {
        return JobId;
    }
}
