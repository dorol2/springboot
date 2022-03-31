package com.example.springpre.dto;

import lombok.*;

@RequiredArgsConstructor    //final 필드 생성자 생성
@Getter
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
