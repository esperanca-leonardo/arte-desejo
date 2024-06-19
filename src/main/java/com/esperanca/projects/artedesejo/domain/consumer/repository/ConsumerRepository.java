package com.esperanca.projects.artedesejo.domain.consumer.repository;

import com.esperanca.projects.artedesejo.domain.consumer.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long>
{
  boolean existsByCpf(String cpf);
  boolean existsByCpfAndIdNot(String cpf, Long id);

  boolean existsByEmail(String email);
  boolean existsByEmailAndIdNot(String email, Long id);

  boolean existsByPhoneNumber(String phoneNumber);
  boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
