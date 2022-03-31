package com.example.springpre.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //  JPA의 class들이 이 클래스를 상속할 경우, 이 클래스의 필드들을 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 auditing 기능 포함
public abstract class BaseTimeEntity {

    @CreatedDate    // 생성 시 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
