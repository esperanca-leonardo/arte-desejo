package com.esperanca.projects.artedesejo.domain.product.service.helper.foreignkey;

import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierIdInput;
import com.esperanca.projects.artedesejo.domain.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductForeignKeyHelperImpl implements ProductForeignKeyHelper
{
  private final SupplierService supplierService;

  @Override
  public Supplier getSupplier(ProductInput productInput)
  {
    final SupplierIdInput supplierIdInput = productInput.getSupplierIdInput();
    final Long supplierId = supplierIdInput.getId();

    return this.supplierService.findById(supplierId);
  }
}
