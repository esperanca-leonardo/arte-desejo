package com.esperanca.projects.artedesejo.domain.saleorder.service.helper.foreignkey;

import com.esperanca.projects.artedesejo.core.contracts.helpers.foreignkey.ForeignKeyHelper;
import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;

public interface SaleOrderForeignKeyHelper
    extends ForeignKeyHelper<Consumer, SaleOrderInput>
{

  @Override
  Consumer getEntity(SaleOrderInput saleOrderInput);
}
