package com.programmers.summerinternship.demo.repository;

import com.programmers.summerinternship.demo.model.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TodoRepository {
    @Insert("INSERT INTO TODO (seq, title, contents, endAt, priority) VALUES (#{seq}, #{title}, #{contents}, #{endAt}, #{priority});")
    @SelectKey(statement = "SELECT nextval('seq_todo')", before = true ,keyProperty = "seq", resultType = Long.class)
    Long save(Todo todo);

    @Select("SELECT * FROM TODO ORDER BY priority DESC, endAt OFFSET #{offset} LIMIT #{limit}" )
    List<Todo> selectAll(long offset, int limit);

    @Select("SELECT * FROM TODO WHERE seq = #{seq}")
    Todo select(Long seq);
}
