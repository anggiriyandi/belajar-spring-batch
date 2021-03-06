/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anggi
 */

@RestController
public class PesertaBatchJobController {
    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    @Qualifier("importDataPesertaFromCsvJob")
    private Job importDataPesertaFromCsvJob;
    
    @Autowired
    @Qualifier("exportPesertaJob")
    private Job exportPesertaJob;
    
    Logger logger = LoggerFactory.getLogger(PesertaBatchJobController.class);
    
    @GetMapping("/runPesertaJob")
    public String runPesertaBatchJob(){
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("JobId", "30")
                    .toJobParameters();
            jobLauncher.run(importDataPesertaFromCsvJob, parameters);
        } catch (Exception ex) {
            logger.error("ERROR LAUNCH importDataPesertaFromCsvJob : ",ex.getMessage(),ex);
            return "ERROR LAUNCH importDataPesertaFromCsvJob : "+ex.getMessage();
        } 
        return "JOB DONE !!";
    }
    
    @GetMapping("/runExportPesertaJob")
    public String runExportPesertaBatchJob(){
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("JobId", "31")
                    .toJobParameters();
            jobLauncher.run(exportPesertaJob, parameters);
        } catch (Exception ex) {
            logger.error("ERROR LAUNCH importDataPesertaFromCsvJob : ",ex.getMessage(),ex);
            return "ERROR LAUNCH importDataPesertaFromCsvJob : "+ex.getMessage();
        } 
        return "JOB DONE !!";
    }
}
