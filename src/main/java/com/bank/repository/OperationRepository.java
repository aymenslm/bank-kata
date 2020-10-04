package com.bank.repository;

import com.bank.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,String> {

    List<Operation> findAllByAccount_Id(String id);
}
