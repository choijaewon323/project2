package com.jaewon.myproject2.dto;

import com.jaewon.myproject2.entity.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReportRequestDto {
    private Long reportId;
    private String content;

    public Report toEntity() {
        return new Report(content);
    }
}
