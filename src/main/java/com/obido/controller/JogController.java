package com.obido.controller;

import com.obido.domain.JogBean;
import com.obido.service.JogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
class JogController {

    @Autowired
    private JogService jogService;

    @RequestMapping(value = "/jog/{jogId}", method = RequestMethod.GET)
    public JogBean retrieveJog(@PathVariable Long jogId) {
        return jogService.retrieveJog(jogId);
    }

    @RequestMapping(value = "/jog/{jogId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJog(@PathVariable Long jogId) {
        jogService.deleteJog(jogId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/jog/{userId}")
    public ResponseEntity<String> createJog(@PathVariable Long userId, @RequestBody JogBean jogBean) {
        Long jogId = jogService.save(userId, jogBean);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(jogId).toUri()).build();
    }

    @PutMapping(value = "/jog/{jogId}")
    public ResponseEntity<String> updateJog(@PathVariable Long userId, @RequestBody JogBean jogBean) {
        jogService.update(userId, jogBean);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "/jog/{userId}", method = RequestMethod.GET)
    public List<JogBean> retrieveUserJogs(@PathVariable Long userId) {
        return jogService.readAllByUserId(userId);
    }

}