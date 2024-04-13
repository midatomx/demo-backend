package com.tracking.demo.service;

import com.tracking.demo.dto.CartypeDTO;

import javax.validation.Valid;
import java.util.List;

public interface ICartypeService {

  CartypeDTO save(@Valid CartypeDTO cartype);

  CartypeDTO get(Long id);

  List<CartypeDTO> getAll();

  void delete(Long id);

}
