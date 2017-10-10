/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.config;

import java.io.IOException;
import java.io.Writer;
import org.springframework.batch.item.file.FlatFileHeaderCallback;

/**
 *
 * @author anggi
 */
public class StringHeaderWritter implements FlatFileHeaderCallback {
    
    private final String header;

    public StringHeaderWritter(String header) {
        this.header = header;
    }
   
    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }
}
