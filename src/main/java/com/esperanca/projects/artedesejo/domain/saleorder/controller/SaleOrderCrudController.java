package com.esperanca.projects.artedesejo.domain.saleorder.controller;

import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderInput;
import com.esperanca.projects.artedesejo.domain.saleorder.models.SaleOrderOutput;
import com.esperanca.projects.artedesejo.domain.saleorder.service.crud.SaleOrderCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/sale-orders")
@AllArgsConstructor
public class SaleOrderCrudController
{
  private final SaleOrderCrudService service;

  @GetMapping
  @ResponseStatus(OK)
  public List<SaleOrderOutput> findAll()
  {
    return this.service.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public SaleOrderOutput findById(@PathVariable Long id)
  {
    return this.service.findById(id);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public SaleOrderOutput save(@Valid @RequestBody SaleOrderInput saleOrderInput)
  {
    return this.service.save(saleOrderInput);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public SaleOrderOutput updateById(@PathVariable Long id,
                                    @Valid @RequestBody SaleOrderInput saleOrderInput)
  {
    return this.service.updateById(id, saleOrderInput);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteById(@PathVariable Long id)
  {
    this.service.deleteById(id);
  }
}
