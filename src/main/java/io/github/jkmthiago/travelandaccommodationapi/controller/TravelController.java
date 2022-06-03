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

import io.github.jkmthiago.travelandaccommodationapi.model.Travel;
import io.github.jkmthiago.travelandaccommodationapi.service.TravelService;

@RestController
@RequestMapping("/api-travels/travels")

public class TravelController {
    private static final Logger logger = Logger.getLogger(TravelController.class);

    @Autowired
    private TravelService travelService;

    @GetMapping
    public ResponseEntity<List<Travel>> find(){
        if(travelService.find().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        logger.info(travelService.find());
        return ResponseEntity.ok(travelService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(){
        try {
            travelService.delete();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Travel> create(@RequestBody JSONObject travel){    
        try {
            if (travelService.isJSONValid(travel.toString())) {
                Travel travelCreated = travelService.create(travel);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(travelCreated.getOrderNumber()).build().toUri();
            
                if (travelService.isStartDateGreaterThanEndDate(travelCreated)) {
                    logger.error("Data de ida é a frente da data de volta");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
                } else {
                    travelService.add(travelCreated);
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
    public ResponseEntity<Travel> update(@PathVariable("id") Long id, @RequestBody JSONObject travel){
        try {
            if (travelService.isJSONValid(travel.toString())) {
                Travel travelToUpdated = travelService.findById(id);
                if (travelToUpdated == null) {
                    logger.error("Viagem não encontrada");
                    return ResponseEntity.notFound().build();
                } else {
                    Travel travelToUpdate = travelService.update(travelToUpdated, travel);
                    return ResponseEntity.ok(travelToUpdate);
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
