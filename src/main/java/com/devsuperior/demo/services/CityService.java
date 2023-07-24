package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

}


