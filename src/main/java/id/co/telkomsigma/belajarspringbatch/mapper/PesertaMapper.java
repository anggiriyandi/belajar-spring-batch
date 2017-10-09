/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.mapper;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 * @author anggi
 */
public class PesertaMapper implements FieldSetMapper<Peserta>{

    @Override
    public Peserta mapFieldSet(FieldSet fs) throws BindException {
        Peserta peserta = new Peserta();
        peserta.setNama(fs.readRawString(0));
        peserta.setAlamat(fs.readString("alamat"));
        peserta.setTanggalLahir(fs.readDate(2));
        return peserta;
    }
    
}
