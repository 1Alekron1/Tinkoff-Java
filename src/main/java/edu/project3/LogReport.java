package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogReport {
    private String[] fileNames;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int totalRequests;
    private long totalBytesSent;
    private Map<String, Long> resourceCounts;
    private Map<Integer, Long> statusCounts;

    public LogReport() {
    }

    public LogReport(
        String[] fileNames, OffsetDateTime startDate, OffsetDateTime endDate,
        int totalRequests, long totalBytesSent, Map<String, Long> resourceCounts,
        Map<Integer, Long> statusCounts
    ) {
        this.fileNames = fileNames;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalRequests = totalRequests;
        this.totalBytesSent = totalBytesSent;
        this.resourceCounts = resourceCounts;
        this.statusCounts = statusCounts;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public long getTotalBytesSent() {
        return totalBytesSent;
    }

    public void setTotalBytesSent(long totalBytesSent) {
        this.totalBytesSent = totalBytesSent;
    }

    public Map<String, Long> getResourceCounts() {
        return resourceCounts;
    }

    public void setResourceCounts(Map<String, Long> resourceCounts) {
        this.resourceCounts = resourceCounts;
    }

    public Map<Integer, Long> getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(Map<Integer, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }

    public static LogReport generateReport(List<LogRecord> logRecords) {
        LogReport logReport = new LogReport();

        logReport.setTotalRequests(logRecords.size());
        logReport.setTotalBytesSent(logRecords.stream().mapToLong(LogRecord::getBodyBytesSent).sum());

        logReport.setResourceCounts(logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getRequest, Collectors.counting())));

        logReport.setStatusCounts(logRecords.stream()
            .collect(Collectors.groupingBy(LogRecord::getStatus, Collectors.counting())));

        return logReport;
    }
}
