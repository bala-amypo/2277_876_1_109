package com.example.demo.repository;

import com.example.demo.entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRecordRepository extends JpaRepository<CreditCardRecord, Long> {

    List<CreditCardRecord> findByUserId(Long userId);

    List<CreditCardRecord> findByUserIdAndActiveTrue(Long userId);
}
