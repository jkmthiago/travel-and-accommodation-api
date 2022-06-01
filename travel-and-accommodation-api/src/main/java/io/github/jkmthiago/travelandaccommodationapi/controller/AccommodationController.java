package io.github.jkmthiago.travelandaccommodationapi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jkmthiago.travelandaccommodationapi.model.Accommodation;
import io.github.jkmthiago.travelandaccommodationapi.service.AccommodationService;

@RestController
@RequestMapping("/api-accommodations/accommodations")

public class AccommodationController {
    private static final Logger logger = Logger.getLogger(AccommodationController.class);

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<List<Accommodation>> find(){
        if(accommodationService.find().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        logger.info(accommodationService.find());
        return ResponseEntity.ok(accommodationService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(){
        try {
            accommodationService.delete();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Accommodation> create(@RequestBody JSONObject accommodation){    
        try {
            if (accommodationService.isJSONValid(accommodation.toString())) {
                Accommodation accommodationCreated = accommodationService.create(accommodation);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(accommodationCreated.getOrderNumber()).build().toUri();
            
                if (accommodationService.isStartDateGreaterThanEndDate(accommodationCreated)) {
                    logger.error("Data de ida é a frente da data de volta");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
                } else {
                    accommodationService.add(accommodationCreated);
                    return ResponseEntity.created(uri).body(null);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("Os campos de JSON não são interpretáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
    
    @PutMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<Accommodation> update(@PathVariable("id") Long id, @RequestBody JSONObject accommodation){
        try {
            if (accommodationService.isJSONValid(accommodation.toString())) {
                Accommodation accommodationToUpdated = accommodationService.findById(id);
                if (accommodationToUpdated == null) {
                    logger.error("Viagem não encontrada");
                    return ResponseEntity.notFound().build();
                } else {
                    Accommodation accommodationToUpdate = accommodationService.update(accommodationToUpdated, accommodation);
                    return ResponseEntity.ok(accommodationToUpdate);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            logger.error("Os campos de JSON não são interpretáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
