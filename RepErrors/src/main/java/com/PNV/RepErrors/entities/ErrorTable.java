package com.PNV.RepErrors.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="error")
public class ErrorTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String errorCode;

    public ErrorTable() {
    }

    public Long getId() {
        return id;
    }

    public ErrorTable(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
