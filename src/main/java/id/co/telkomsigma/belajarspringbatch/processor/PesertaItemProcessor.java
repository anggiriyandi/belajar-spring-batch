/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.processor;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author anggi
 */
@Component
public class PesertaItemProcessor implements ItemProcessor<Peserta, Peserta>{

    @Override
    public Peserta process(Peserta peserta) throws Exception {
        final Peserta p = peserta;
        p.setNama(peserta.getNama().toUpperCase());
        return p;
    }
}
