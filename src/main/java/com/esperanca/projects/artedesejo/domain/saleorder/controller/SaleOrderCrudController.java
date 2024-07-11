package com.esperanca.projects.artedesejo.domain.saleorder.controller;

import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;
import com.esperanca.projects.artedesejo.domain.saleorder.service.crud.SaleOrderCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-orders")
@AllArgsConstructor
public class SaleOrderCrudController
{
  private final SaleOrderCrudService service;

  @GetMapping
  public List<SaleOrderOutput> findAll()
  {
    return this.service.findAll();
  }

  @GetMapping("/{id}")
  public SaleOrderOutput findById(@PathVariable Long id)
  {
    return this.service.findById(id);
  }

  @PostMapping
  public SaleOrderOutput save(@Valid @RequestBody SaleOrderInput saleOrderInput)
  {
    return this.service.save(saleOrderInput);
  }

  @PutMapping("/{id}")
  public SaleOrderOutput updateById(@PathVariable Long id,
                                    @Valid @RequestBody SaleOrderInput saleOrderInput)
  {
    return this.service.updateById(id, saleOrderInput);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id)
  {
    this.service.deleteById(id);
  }
}