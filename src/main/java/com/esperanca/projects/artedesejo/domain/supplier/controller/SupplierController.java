package com.esperanca.projects.artedesejo.domain.supplier.controller;

import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierInput;
import com.esperanca.projects.artedesejo.domain.supplier.models.SupplierOutput;
import com.esperanca.projects.artedesejo.domain.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController
{
  private final SupplierService service;

  @GetMapping
  @ResponseStatus(OK)
  public List<SupplierOutput> findAll()
  {
    return this.service.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public SupplierOutput findAll(@PathVariable Long id)
  {
    return this.service.findById(id);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public SupplierOutput save(@RequestBody SupplierInput supplierInput)
  {
    return this.service.save(supplierInput);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public SupplierOutput updateById(@PathVariable Long id,
                                   @RequestBody SupplierInput supplierInput)
  {
    return this.service.updateById(id, supplierInput);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteById(@PathVariable Long id)
  {
    this.service.deleteById(id);
  }
}
