package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService service;

  @PutMapping("/{id}")
  public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO event) {
    var result = service.update(id, event);
    return ResponseEntity.ok().body(result);
  }
}
