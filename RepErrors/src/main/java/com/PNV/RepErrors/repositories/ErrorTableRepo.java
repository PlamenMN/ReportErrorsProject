package com.PNV.RepErrors.repositories;

import com.PNV.RepErrors.entities.ErrorTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorTableRepo extends JpaRepository<ErrorTable,Long> {
    ErrorTable findErrByErrorCode(String Name);
    ErrorTable findErrorTableById(Long id);
}