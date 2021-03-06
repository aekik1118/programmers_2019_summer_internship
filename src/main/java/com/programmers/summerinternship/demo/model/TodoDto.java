package com.programmers.summerinternship.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class TodoDto {

    private String title;

    private String contents;

    private LocalDateTime deadline;

    private Long priority;

    private boolean isDone;

    private boolean hasDeadline;
}
