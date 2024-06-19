package com.esperanca.projects.artedesejo.domain.consumer.controller;

import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerInput;
import com.esperanca.projects.artedesejo.domain.consumer.models.ConsumerOutput;
import com.esperanca.projects.artedesejo.domain.consumer.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class ConsumerController
{
  private final ConsumerService service;

  @GetMapping
  @ResponseStatus(OK)
  public List<ConsumerOutput> findAll()
  {
    return this.service.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public ConsumerOutput findById(@PathVariable Long id)
  {
    return this.service.findById(id);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public ConsumerOutput save(@RequestBody ConsumerInput consumerInput)
  {
    return this.service.save(consumerInput);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public ConsumerOutput updateById(@RequestBody ConsumerInput consumerInput,
                                   @PathVariable Long id)
  {
    return this.service.updateById(consumerInput, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteById(@PathVariable Long id)
  {
    this.service.deleteById(id);
  }
}
