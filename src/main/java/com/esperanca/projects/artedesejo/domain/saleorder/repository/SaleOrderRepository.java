package com.esperanca.projects.artedesejo.domain.saleorder.repository;

import com.esperanca.projects.artedesejo.domain.saleorder.entity.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long>
{

}
