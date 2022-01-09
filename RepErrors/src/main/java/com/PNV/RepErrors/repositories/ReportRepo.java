package com.PNV.RepErrors.repositories;

import com.PNV.RepErrors.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface ReportRepo extends JpaRepository<Report,Long> {
    Report findReportById(Long id);
    Report findReportByIsFixed(Boolean fixed);
}
