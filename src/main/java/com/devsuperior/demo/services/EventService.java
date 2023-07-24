package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  private EventRepository repository;

  @Autowired
  private CityRepository cityRepository;

  public EventDTO update(Long id, EventDTO event) {
      var result = repository.findById(id);
      var entity = result.orElseThrow(() -> new NotFoundException("Event not found"));
      BeanUtils.copyProperties(event, entity);
      var city = cityRepository.getReferenceById(event.getCityId());
      entity.setId(id);
      entity.setCity(city);
      repository.save(entity);
      return new EventDTO(entity);
  }
}
