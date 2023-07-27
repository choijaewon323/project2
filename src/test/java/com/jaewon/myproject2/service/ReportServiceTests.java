package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.ReportRequestDto;
import com.jaewon.myproject2.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReportServiceTests {
    @Autowired
    ReportService reportService;
    @Autowired
    ReportRepository reportRepository;

    @AfterEach
    void afterEach() {
        reportRepository.deleteAll();
    }

    @Test
    void createTest() {
        ReportRequestDto reportRequestDto = new ReportRequestDto();

        reportRequestDto.setContent("report");

        // test
        reportService.create(reportRequestDto);

        assertThat(reportRepository.findAll().get(0).getContent()).isEqualTo("report");
    }
}
