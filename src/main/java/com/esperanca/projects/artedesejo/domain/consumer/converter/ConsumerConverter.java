package com.esperanca.projects.artedesejo.domain.consumer.converter;

import com.esperanca.projects.artedesejo.core.utils.converter.Converter;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;

import java.util.List;

public interface ConsumerConverter
    extends Converter<Consumer, ConsumerInput, ConsumerOutput>
{
  @Override
  Consumer toEntity(ConsumerInput consumerInput);

  @Override
  ConsumerOutput toOutput(Consumer entity);

  @Override
  List<ConsumerOutput> toCollectionOutput(List<Consumer> entities);
}
