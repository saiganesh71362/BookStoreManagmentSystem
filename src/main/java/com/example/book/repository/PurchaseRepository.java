package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.entity.Purchase;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long>{

}
