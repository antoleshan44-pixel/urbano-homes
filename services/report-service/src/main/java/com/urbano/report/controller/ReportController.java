package com.urbano.report.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.report.dto.ReportDto;
import com.urbano.report.dto.ReportRequest;
import com.urbano.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDto> generateReport(@Valid @RequestBody ReportRequest request) {
        return ResponseEntity.ok(reportService.generateReport(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable UUID id) {
        return ResponseEntity.ok(reportService.getReport(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PagedResponse<ReportDto>> getUserReports(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(reportService.getUserReports(userId, page, size));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadReport(@PathVariable UUID id) {
        byte[] reportData = reportService.downloadReport(id);
        ReportDto report = reportService.getReport(id);
        String filename = report.getName() + "." + report.getFormat().toLowerCase();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(reportData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable UUID id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
