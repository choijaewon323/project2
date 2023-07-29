package com.jaewon.myproject2.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class DateTime {

    private LocalDateTime createdTime;

    @PrePersist     // when created
    public void prePersist() {
        this.createdTime = LocalDateTime.now();
    }
}
