package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {

     @Autowired
     private UbicacionBodegaRepository ubicacionBodegaRepository;

     @Transactional
     public UbicacionBodegaEntity crearUbicacionBodega(UbicacionBodegaEntity ubicacionBodega) throws IllegalOperationException{
        log.info("se inicia la creacion de la ubicacion de una bodega");
        if(ubicacionBodega.getNumeroEstante()<0)
            throw new IllegalOperationException("el numero de estantes no puede ser negativo");
        return ubicacionBodegaRepository.save(ubicacionBodega);

     }

}
