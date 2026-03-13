package com.dealership.proiect.repository;

import com.dealership.proiect.model.Dotare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DotareRepository extends JpaRepository<Dotare, Integer> {
}