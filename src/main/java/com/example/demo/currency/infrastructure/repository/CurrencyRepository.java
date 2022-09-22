package com.example.demo.currency.infrastructure.repository;

import com.example.demo.currency.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CurrencyRepository extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Long> {

}
