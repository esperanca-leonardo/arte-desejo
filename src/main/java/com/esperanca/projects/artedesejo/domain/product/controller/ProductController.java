package com.esperanca.projects.artedesejo.domain.product.controller;

import com.esperanca.projects.artedesejo.domain.product.models.ProductInput;
import com.esperanca.projects.artedesejo.domain.product.models.ProductOutput;
import com.esperanca.projects.artedesejo.domain.product.service.crud.ProductCrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController
{
  private final ProductCrudService service;

  @GetMapping
  @ResponseStatus(OK)
  public List<ProductOutput> findAll()
  {
    return this.service.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public ProductOutput findById(@PathVariable Long id)
  {
    return this.service.findById(id);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public ProductOutput save(@RequestBody @Valid ProductInput productInput)
  {
    return this.service.save(productInput);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public ProductOutput updateById(@PathVariable Long id,
                                  @Valid @RequestBody ProductInput productInput)
  {
    return this.service.updateById(id, productInput);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteById(@PathVariable Long id)
  {
    this.service.deleteById(id);
  }
}
