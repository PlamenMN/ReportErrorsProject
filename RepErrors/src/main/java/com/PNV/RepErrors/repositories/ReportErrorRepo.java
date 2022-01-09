package com.PNV.RepErrors.repositories;

import com.PNV.RepErrors.entities.ErrorTable;
import com.PNV.RepErrors.entities.Report;
import com.PNV.RepErrors.entities.ReportError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.Delete;
import org.springframework.web.bind.annotation.DeleteMapping;

public interface ReportErrorRepo extends  JpaRepository<ReportError,Long> {
    ReportError findReportErrorByDescription(String name);
    ReportError findReportErrorByErrorTable(ErrorTable errorTable);
    ReportError removeReportErrorByReportId(Long id);
}
