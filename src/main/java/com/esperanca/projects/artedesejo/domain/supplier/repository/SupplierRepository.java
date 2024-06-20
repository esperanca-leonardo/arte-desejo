package com.esperanca.projects.artedesejo.domain.supplier.repository;

import com.esperanca.projects.artedesejo.domain.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>
{ }
