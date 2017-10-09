/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.telkomsigma.belajarspringbatch.dao;

import id.co.telkomsigma.belajarspringbatch.domain.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author anggi
 */
public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
    
}
