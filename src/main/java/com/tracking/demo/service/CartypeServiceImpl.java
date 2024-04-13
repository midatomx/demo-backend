package com.tracking.demo.service;

import com.tracking.demo.dto.CartypeDTO;
import com.tracking.demo.model.Cartype;
import com.tracking.demo.repository.ICartypeJpaRepository;
import com.tracking.demo.service.exception.CartypeNotFoundException;
import com.tracking.demo.service.exception.TechnicalException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CartypeServiceImpl implements ICartypeService {

  @Autowired
  private ICartypeJpaRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<CartypeDTO> getAll() {
    List<Cartype> listCartypeEntity = repository.findAll();
    List<CartypeDTO> listCartypeDto = listCartypeEntity.stream()
      .map(cartype -> modelMapper.map(cartype, CartypeDTO.class))
      .collect(Collectors.toList());
    return listCartypeDto;
  }

  @Override
  public CartypeDTO get(Long id) throws CartypeNotFoundException, TechnicalException {
    CartypeDTO cartypeDTO;
    try {
//      Cartype cartypeEntity = repository.getOne(id); // deprecated
      Cartype cartypeEntity = repository.findById(id).get();
      cartypeEntity.getName();
      cartypeDTO = modelMapper.map(cartypeEntity, CartypeDTO.class);
    } catch (CartypeNotFoundException e) {
      throw new CartypeNotFoundException(id, e);
    } catch (Exception e) {
      throw new TechnicalException(e);
    }
    return cartypeDTO;
  }

  @Override
  public CartypeDTO save(CartypeDTO cartypeDTO) {
    Cartype cartypeEntity = modelMapper.map(cartypeDTO, Cartype.class);

    if (cartypeDTO.getId() != null) {
      cartypeEntity.setId(cartypeDTO.getId());
    }
    cartypeEntity = repository.save(cartypeEntity);
    return modelMapper.map(cartypeEntity, CartypeDTO.class);
  }

  @Override
  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new CartypeNotFoundException(id, e);
    } catch (Exception e) {
      throw new TechnicalException(e);
    }

  }


}
