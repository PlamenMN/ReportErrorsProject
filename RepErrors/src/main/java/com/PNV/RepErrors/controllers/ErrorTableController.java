package com.PNV.RepErrors.controllers;

import com.PNV.RepErrors.entities.*;
import com.PNV.RepErrors.entities.keys.ReportErrorKey;
import com.PNV.RepErrors.entities.payload.request.ErrorTableRequest;
import com.PNV.RepErrors.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*")
public class ErrorTableController {
    private final ErrorTableRepo errorTableRepo;
    private final ClientRepo clientRepo;
    private final ReportErrorRepo reportErrorRepo;
    private final ReportRepo reportRepo;

    ErrorTableController(ErrorTableRepo errorTableRepo,
                         ClientRepo clientRepo,
                         ReportErrorRepo reportErrorRepo,
                         ReportRepo reportRepo) {
        this.errorTableRepo = errorTableRepo;
        this.clientRepo=clientRepo;
        this.reportErrorRepo=reportErrorRepo;
        this.reportRepo=reportRepo;
    }

    @GetMapping("/all/reports")
    public List<Report> getAllReports(){
        return  reportRepo.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistReport(@RequestBody ErrorTableRequest errorTableRequest) {
        Client client=clientRepo.findClientByUsername(errorTableRequest.getUsername());

        ErrorTable errorTable=errorTableRepo.save(new ErrorTable(errorTableRequest.getErrorCode()));

        Report newReport =reportRepo.save(new Report(client,new Timestamp(System.currentTimeMillis()),
                errorTableRequest.getTypeofError(),false));

        ReportError reportError=reportErrorRepo.save(new ReportError(new ReportErrorKey(newReport.getId()
                ,errorTable.getId()),
                newReport,errorTable,errorTableRequest.getDescription()));

        return ResponseEntity.ok("New Report created with id=" +
                reportError.getId()+"!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> persistDeleteReport(Long id) {
        Optional<Report> report = Optional.ofNullable(reportRepo.findReportById(id));
        ErrorTable errorTable=errorTableRepo.findErrorTableById(id);
        if (report.isEmpty()) {
            return ResponseEntity.ok("Report not found!");
        }
        reportErrorRepo.removeReportErrorByReportId(id);
        reportRepo.delete(report.get());
        errorTableRepo.delete(errorTable);
        return ResponseEntity.ok("Report: "+id+" was Deleted!");
    }
}