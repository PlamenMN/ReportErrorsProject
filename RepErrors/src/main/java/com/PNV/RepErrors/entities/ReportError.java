package com.PNV.RepErrors.entities;

import com.PNV.RepErrors.entities.keys.ReportErrorKey;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class ReportError {
    @EmbeddedId
    private ReportErrorKey id;

    @ManyToOne
    @MapsId("reportId")
    @JoinColumn(name="report_id")
    private Report report;

    @ManyToOne
    @MapsId("errorId")
    @JoinColumn(name="error_id")
    private ErrorTable errorTable;


    public ReportError() {
    }

    public ReportError(ReportErrorKey id, Report report, ErrorTable errorTable, String description) {
        this.id = id;
        this.report = report;
        this.errorTable = errorTable;
        this.description = description;
    }

    private String description;

    public ReportErrorKey getId() {
        return id;
    }

    public void setId(ReportErrorKey id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ErrorTable getErrorTable() {
        return errorTable;
    }

    public void setErrorTable(ErrorTable errorTable) {
        this.errorTable = errorTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
