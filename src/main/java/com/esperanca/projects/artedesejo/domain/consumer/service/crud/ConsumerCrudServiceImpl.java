package com.esperanca.projects.artedesejo.domain.consumer.service.crud;

import com.esperanca.projects.artedesejo.core.contracts.helpers.copier.CopierHelper;
import com.esperanca.projects.artedesejo.domain.consumer.converter.ConsumerConverterHelper;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerInUseException;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.consumer.exceptions.crud.ConsumerNotFoundException;
import com.esperanca.projects.artedesejo.domain.consumer.checker.ConsumerChecker;
import com.esperanca.projects.artedesejo.domain.consumer.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsumerCrudServiceImpl implements ConsumerCrudService
{
  private final CopierHelper copier;
  private final ConsumerChecker validator;
  private final ConsumerRepository repository;
  private final ConsumerConverterHelper converter;

  @Override
  public List<ConsumerOutput> findAll()
  {
    final List<Consumer> consumers = this.repository.findAll();
    return this.converter.toCollectionOutput(consumers);
  }

  @Override
  public ConsumerOutput findById(Long id) throws ConsumerNotFoundException
  {
    return this.repository.findById(id)
        .map(this.converter::toOutput)
        .orElseThrow(() -> new ConsumerNotFoundException(id));
  }

  @Override
  public ConsumerOutput save(ConsumerInput consumerInput)
  {
    this.validator.validateNewConsumerData(consumerInput);

    Consumer entity = this.converter.toEntity(consumerInput);
    entity = this.repository.save(entity);
    return this.converter.toOutput(entity);
  }

  @Override
  public ConsumerOutput updateById(Long id, ConsumerInput consumerInput)
      throws ConsumerNotFoundException
  {
    Consumer savedConsumer = this.repository.findById(id)
        .orElseThrow(() -> new ConsumerNotFoundException(id));

    this.validator.validateExistingConsumerData(consumerInput, id);
    this.copier.copyProperties(consumerInput, savedConsumer);

    savedConsumer = this.repository.save(savedConsumer);
    return this.converter.toOutput(savedConsumer);
  }

  @Override
  public void deleteById(Long id) throws ConsumerInUseException
  {
    this.findById(id);

    try
    {
      this.repository.deleteById(id);
    }
    catch (DataIntegrityViolationException exception)
    {
      throw new ConsumerInUseException(id);
    }
  }
}
