package com.esperanca.projects.artedesejo.domain.product.service.helper.foreignkey;

import com.esperanca.projects.artedesejo.core.contracts.helpers.foreignkey.ForeignKeyHelper;
import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;

public interface ProductForeignKeyHelper
    extends ForeignKeyHelper<Supplier, ProductInput>
{
  @Override
  Supplier getEntity(ProductInput productInput);
}
