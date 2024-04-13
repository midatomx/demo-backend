package com.tracking.demo.service;

import com.tracking.demo.dto.ProspectDTO;

import javax.validation.Valid;
import java.util.List;

public interface IProspectService {

  ProspectDTO save(@Valid ProspectDTO prospect);

  ProspectDTO get(Long id);

  List<ProspectDTO> getAll();

  void delete(Long id);

  int countAllFunction();

  List<ProspectDTO> getAllProcedure();


}
