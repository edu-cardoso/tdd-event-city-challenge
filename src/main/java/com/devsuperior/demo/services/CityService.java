package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

  @Autowired
  private CityRepository repository;

  @Transactional(readOnly = true)
  public List<CityDTO> findAll() {
    var result = repository.findAll(Sort.by("name"));
    return result.stream().map(CityDTO::new).toList();
  }

  @Transactional
  public CityDTO insert(CityDTO dto) {
    var city = new City();
    city.setName(dto.getName());
    city = repository.save(city);
    return new CityDTO(city);
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("City not found");
    }
    try {
      repository.deleteById(id);
    }
    catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Referential integrity failure");
    }
  }


}


