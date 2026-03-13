package com.dealership.proiect.repository;

import com.dealership.proiect.model.Finantare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinantareRepository extends JpaRepository<Finantare, Integer> {
}