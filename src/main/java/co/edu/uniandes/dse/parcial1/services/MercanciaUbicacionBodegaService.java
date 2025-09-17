package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

    @Autowired
    MercanciaRepository mercanciaRepository;
    @Autowired
    UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public void asociarMercancia(Long mercanciaId, Long ubicacionBodegaId){
        log.info("Inicia el proceso de asociar una mercancia a la bodega con id= {0}",ubicacionBodegaId);
        var mercancia = mercanciaRepository.findById(mercanciaId).orElseThrow();
        var ubicacionBodega = ubicacionBodegaRepository.findById(ubicacionBodegaId).orElseThrow();
        mercancia.setUbicacionBodega(ubicacionBodega);
        mercanciaRepository.save(mercancia);
        log.info("Termina el proceso de asociaruna mercancia a la bodega con id= {0}",ubicacionBodegaId);
    }

}
