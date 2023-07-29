package com.jaewon.myproject2.entity;


import com.jaewon.myproject2.common.DateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REPORT")
public class Report extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPORT_ID")
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    public Report(String content) {
        this.content = content;
    }
}
