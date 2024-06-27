package com.esperanca.projects.artedesejo.domain.product.service.helper.foreignkey;

import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;

public interface ProductForeignKeyHelper
{
  Supplier getSupplier(ProductInput productInput);
}
