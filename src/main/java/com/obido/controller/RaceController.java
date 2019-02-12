package com.obido.controller;

import com.obido.domain.RaceBean;
import com.obido.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
class RaceController {

    @Autowired
    private RaceService raceService;

    @RequestMapping(value = "/race/{raceId}", method = RequestMethod.GET)
    public RaceBean retrieveRace(@PathVariable Long raceId) {
        return raceService.retrieveRace(raceId);
    }

    @RequestMapping(value = "/race/{raceId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRace(@PathVariable Long raceId) {
        raceService.deleteRace(raceId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/race/{userId}")
    public ResponseEntity<String> createRace(@PathVariable Long userId, @RequestBody RaceBean raceBean) {
        Long raceId = raceService.save(userId, raceBean);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(raceId).toUri()).build();
    }

    @PutMapping(value = "/race/{raceId}")
    public ResponseEntity<String> updateRace(@PathVariable Long userId, @RequestBody RaceBean raceBean) {
        raceService.update(userId, raceBean);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/races/{userId}", method = RequestMethod.GET)
    public List<RaceBean> retrieveUserRaces(@PathVariable Long userId) {
        return raceService.readAllByUserId(userId);
    }

}