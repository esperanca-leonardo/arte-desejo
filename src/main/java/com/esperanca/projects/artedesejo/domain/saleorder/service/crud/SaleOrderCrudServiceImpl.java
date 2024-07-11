package com.esperanca.projects.artedesejo.domain.saleorder.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.helpers.copier.CopierHelper;
import com.esperanca.projects.artedesejo.core.exceptions.EntityNotFoundException;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.saleorder.converter.SaleOrderConverter;
import com.esperanca.projects.artedesejo.domain.saleorder.entity.SaleOrder;
import com.esperanca.projects.artedesejo.domain.saleorder.exceptions.SaleOrderNotFoundException;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;
import com.esperanca.projects.artedesejo.domain.saleorder.repository.SaleOrderRepository;
import com.esperanca.projects.artedesejo.domain.saleorder.service.helper.foreignkey.SaleOrderForeignKeyHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleOrderCrudServiceImpl implements SaleOrderCrudService
{
  private final CopierHelper copierHelper;
  private final SaleOrderConverter converter;
  private final SaleOrderRepository repository;
  private final SaleOrderForeignKeyHelper foreignKeyHelper;

  @Override
  public List<SaleOrderOutput> findAll()
  {
    return this.repository.findAll()
        .stream()
        .map(this.converter::toOutput)
        .toList();
  }

  @Override
  public SaleOrderOutput findById(Long id) throws EntityNotFoundException
  {
    return this.repository.findById(id)
        .map(this.converter::toOutput)
        .orElseThrow(() -> new SaleOrderNotFoundException(id));
  }

  @Override
  public SaleOrderOutput save(SaleOrderInput saleOrderInput)
  {
    final Consumer consumer = this.foreignKeyHelper.getEntity(saleOrderInput);
    SaleOrder saleOrder = this.converter.toEntity(saleOrderInput);

    saleOrder.setConsumer(consumer);
    saleOrder = this.repository.save(saleOrder);

    return this.converter.toOutput(saleOrder);
  }

  @Override
  public SaleOrderOutput updateById(Long id, SaleOrderInput saleOrderInput)
      throws EntityNotFoundException
  {
    SaleOrder saleOrder = this.repository.findById(id)
        .orElseThrow(() -> new SaleOrderNotFoundException(id));

    final Consumer consumer = this.foreignKeyHelper.getEntity(saleOrderInput);

    saleOrder.setConsumer(consumer);
    this.copierHelper.copyProperties(saleOrderInput, saleOrder);
    saleOrder = this.repository.save(saleOrder);

    return this.converter.toOutput(saleOrder);
  }

  @Override
  public void deleteById(Long id)
  {
    this.findById(id);
    this.repository.deleteById(id);
  }
}
