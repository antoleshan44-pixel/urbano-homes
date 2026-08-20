package com.urbano.report.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.report.dto.ReportDto;
import com.urbano.report.dto.ReportRequest;
import com.urbano.report.entity.Report;
import com.urbano.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public ReportDto generateReport(ReportRequest request) {
        Report report = Report.builder()
                .name(request.getName())
                .type(request.getType())
                .format(request.getFormat())
                .description(request.getDescription())
                .status("PROCESSING")
                .generatedBy(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .build();

        report = reportRepository.save(report);
        log.info("Report generation started: {}", report.getId());

        // Simulate report generation
        report.setStatus("COMPLETED");
        report.setCompletedAt(LocalDateTime.now());
        report.setFilePath("/reports/" + report.getId() + "." + report.getFormat().toLowerCase());
        report.setFileSize(1024L);
        report.setExpiresAt(LocalDateTime.now().plusDays(30));
        report = reportRepository.save(report);

        return mapToDto(report);
    }

    public ReportDto getReport(UUID id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
        return mapToDto(report);
    }

    public PagedResponse<ReportDto> getUserReports(UUID userId, int page, int size) {
        Page<Report> reports = reportRepository
                .findByGeneratedByOrderByCreatedAtDesc(userId, PageRequest.of(page, size));
        List<ReportDto> content = reports.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ReportDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(reports.getTotalElements())
                .totalPages(reports.getTotalPages())
                .first(reports.isFirst())
                .last(reports.isLast())
                .build();
    }

    public byte[] downloadReport(UUID id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found"));
        // In a real implementation, this would read from file system
        return ("Report content for " + report.getName()).getBytes();
    }

    @Transactional
    public void deleteReport(UUID id) {
        reportRepository.deleteById(id);
        log.info("Report deleted: {}", id);
    }

    private ReportDto mapToDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .name(report.getName())
                .type(report.getType())
                .format(report.getFormat())
                .description(report.getDescription())
                .status(report.getStatus())
                .filePath(report.getFilePath())
                .fileSize(report.getFileSize())
                .generatedBy(report.getGeneratedBy())
                .createdAt(report.getCreatedAt())
                .completedAt(report.getCompletedAt())
                .expiresAt(report.getExpiresAt())
                .build();
    }
}
