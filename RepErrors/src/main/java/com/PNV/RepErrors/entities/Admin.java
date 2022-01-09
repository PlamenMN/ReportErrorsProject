package com.PNV.RepErrors.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String email;

    @Column()
    private String username;

    @Column()
    private String password;

    @ManyToMany
    @JoinTable(name="admin_report",
            joinColumns = @JoinColumn(name="adminId"),
            inverseJoinColumns = @JoinColumn(name="reportId"))
    Set<Report> reports;

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Admin() {
    }

    public Admin(String username, String password,String email) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
