package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.ReportRequestDto;
import com.jaewon.myproject2.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public void create(ReportRequestDto reportRequestDto) {
        reportRepository.save(reportRequestDto.toEntity());
    }
}
