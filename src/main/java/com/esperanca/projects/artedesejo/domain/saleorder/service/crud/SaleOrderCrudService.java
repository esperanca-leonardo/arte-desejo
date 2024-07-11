package com.esperanca.projects.artedesejo.domain.saleorder.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.services.crud.CrudService;
import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;

import java.util.List;

public interface SaleOrderCrudService
    extends CrudService<SaleOrderOutput, SaleOrderInput, Long>
{
  @Override
  List<SaleOrderOutput> findAll();

  @Override
  SaleOrderOutput findById(Long id) throws EntityNotFoundException;

  @Override
  SaleOrderOutput save(SaleOrderInput saleOrderInput);

  @Override
  SaleOrderOutput updateById(Long id, SaleOrderInput saleOrderInput)
      throws EntityNotFoundException;

  @Override
  void deleteById(Long id);
}
