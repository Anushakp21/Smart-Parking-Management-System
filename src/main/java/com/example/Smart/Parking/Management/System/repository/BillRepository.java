package com.example.Smart.Parking.Management.System.repository;

import com.example.Smart.Parking.Management.System.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
}
