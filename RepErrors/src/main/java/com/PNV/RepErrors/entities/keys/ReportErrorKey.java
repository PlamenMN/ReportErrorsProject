package com.PNV.RepErrors.entities.keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReportErrorKey implements Serializable {
    private Long reportId;
    private Long errorId;

    public ReportErrorKey() {
    }

    public ReportErrorKey(Long reportId, Long errorId) {
        this.reportId = reportId;
        this.errorId = errorId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportErrorKey that = (ReportErrorKey) o;
        return reportId.equals(that.reportId) && errorId.equals(that.errorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, errorId);
    }
}
