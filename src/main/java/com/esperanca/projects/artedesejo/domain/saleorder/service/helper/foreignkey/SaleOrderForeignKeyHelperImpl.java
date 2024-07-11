package com.esperanca.projects.artedesejo.domain.saleorder.service.helper.foreignkey;

import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerIdInput;
import com.esperanca.projects.artedesejo.domain.consumer.service.ConsumerService;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaleOrderForeignKeyHelperImpl implements SaleOrderForeignKeyHelper
{
  private final ConsumerService consumerService;

  @Override
  public Consumer getEntity(SaleOrderInput saleOrderInput)
  {
    ConsumerIdInput consumerIdInput = saleOrderInput.getConsumerIdInput();
    Long consumerId = consumerIdInput.getId();

    return this.consumerService.findById(consumerId);
  }
}
