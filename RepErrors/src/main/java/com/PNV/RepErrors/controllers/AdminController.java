package com.PNV.RepErrors.controllers;

import com.PNV.RepErrors.entities.*;
import com.PNV.RepErrors.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController{
    private final AdminRepo adminRepo;
    private final ReportRepo reportRepo;
    private final ErrorTableRepo errorTableRepo;
    AdminController(AdminRepo adminRepo,ReportRepo reportRepo,ErrorTableRepo errorTableRepo){
        this.adminRepo=adminRepo;
        this.reportRepo=reportRepo;
        this.errorTableRepo=errorTableRepo;
    }
    @PostMapping("/create")
    public ResponseEntity<?> persistNewAdmin(String username,String password,String email) {
        Admin admin1 =adminRepo.findAdminByUsername(username);
        if(admin1 !=null)
            return ResponseEntity.ok("Admin already exists");
        adminRepo.save(
                new Admin(username,password,email));
        return ResponseEntity.ok("Admin "+username+" is saved.");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> persistDeleteAdmin(String username) {
        Optional<Admin> result= Optional.ofNullable(adminRepo.findAdminByUsername(username));
        if(result.isEmpty())
        {
            return ResponseEntity.ok("Admin not found!");
        }
        adminRepo.delete(result.get());
        return ResponseEntity.ok(username+" was Deleted!");
    }
    @GetMapping("/allreports")
    public List<String> showAllReports() {
        List<Report> reports  =reportRepo.findAll();
        List<String> everything=new ArrayList<>();
        for(int i=0;i<reports.size();i++)
        {
            String info= "Report Number: "+reports.get(i).getId()+"Reported By Client:"
                    +reports.get(i).getClient().getUsername()+ " with Error Type :"
                    +reports.get(i).getTypeOfError()+": report time ="+
                    reports.get(i).getTimestamp().toString();
                    everything.add(info);
        }
        return everything;
    }
    @GetMapping("/allerrors")
    public List<String> showAllErrors() {
        List<ErrorTable> errors  =errorTableRepo.findAll();
        List<String> everything=new ArrayList<>();
        for(int i=0;i<errors.size();i++)
        {
            String info= "Error number: "+errors.get(i).getId()+"Error Code:"
                    +errors.get(i).getErrorCode();
            everything.add(info);
        }
        return everything;
    }
    @PostMapping("/fix")
    public ResponseEntity<?> persistFix(Long id) {
        Report report=reportRepo.findReportById(id);
        report.setFixed(true);
        reportRepo.save(report);
        return ResponseEntity.ok(id+" was fixed!");
    }

}
