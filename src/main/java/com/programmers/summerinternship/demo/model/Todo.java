package com.programmers.summerinternship.demo.model;

import lombok.*;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Builder @Getter @Setter
@EqualsAndHashCode(of = "seq")
@ToString
public class Todo {

    private final Long seq;

    private String title;

    private String contents;

    private final LocalDateTime createAt;

    private LocalDateTime endAt;

    private Long priority;

    private boolean isDone;

    public Todo(String title, String contents, LocalDateTime endAt){
        this(null, title, contents, null, endAt, 0L, false);
    }

    public Todo(TodoDto todoDto){
        this(null, todoDto.getTitle(), todoDto.getContents(), null, todoDto.getEndAt(), todoDto.getPriority(), false);
    }

    public Todo(Long seq, String title, String contents, LocalDateTime createAt, LocalDateTime endAt, Long priority, boolean isDone) {

        checkArgument(isNotBlank(title), "title must be provided.");
        checkArgument(isNotEmpty(contents), "contents must be provided.");
        checkArgument(
                contents.length() >= 1 && contents.length() <= 2000,
                "contents length must be between 1 and 2000 characters."
        );
        checkNotNull(endAt, "endAt must be provided.");


        this.seq = seq;
        this.title = title;
        this.contents = contents;

        if(createAt == null){
            createAt = LocalDateTime.now();
        }

        checkArgument(
                createAt.isBefore(endAt),
                "reateAt은 endAt 보다 이른 시간이여야 합니다."
        );


        this.createAt = createAt;
        this.endAt = endAt;

        if(priority == null){
            priority = 0L;
        }

        this.priority = priority;
        this.isDone = isDone;
    }

}
