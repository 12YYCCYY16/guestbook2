package kr.ac.kopo.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners( value = {AuditingEntityListener.class})
@Getter

public class BaseEntity {

    @CreatedDate
    @Column(name =  "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
