package com.PNV.RepErrors.repositories;

import com.PNV.RepErrors.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin findAdminByUsername(String name);
}
