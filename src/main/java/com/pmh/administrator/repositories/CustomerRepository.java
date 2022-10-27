package com.pmh.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmh.administrator.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
