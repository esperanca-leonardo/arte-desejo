package com.esperanca.projects.artedesejo.domain.consumer.converter;

import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConsumerConverterHelperImpl implements ConsumerConverterHelper
{
  private final ModelMapper modelMapper;

  @Override
  public Consumer toEntity(ConsumerInput consumerInput)
  {
    return this.modelMapper.map(consumerInput, Consumer.class);
  }

  @Override
  public ConsumerOutput toOutput(Consumer entity)
  {
    return this.modelMapper.map(entity, ConsumerOutput.class);
  }

  @Override
  public List<ConsumerOutput> toCollectionOutput(List<Consumer> entities)
  {
    return entities.stream()
        .map(this::toOutput)
        .toList();
  }
}
