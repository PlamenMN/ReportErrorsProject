package com.PNV.RepErrors.repositories;

import com.PNV.RepErrors.entities.Client;
import com.PNV.RepErrors.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client,Long> {
    Client findClientByUsername(String name);
    @Query("SELECT c " +
            "FROM Client c " +
            "WHERE " +
            "lower(c.firstName) " +
            "LIKE :#{#firstName==null || #firstName.isEmpty()?'%':'%'+#firstName+'%'} " +
            "AND lower(c.lastName) " +
            "LIKE :#{#lastName==null || #lastName.isEmpty()?'%':'%'+#lastName+'%'}")
    Page<Client> filterClients(Pageable pageable, String firstName, String lastName);
}
