package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
