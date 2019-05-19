package com.programmers.summerinternship.demo.repository;

import com.programmers.summerinternship.demo.model.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface TodoRepository {
    @Insert("INSERT INTO TODO (seq, title, contents, deadline, priority, hasDeadline) VALUES (#{seq}, #{title}, #{contents}, #{deadline}, #{priority}, #{hasDeadline});")
    @SelectKey(statement = "SELECT nextval('seq_todo')", before = true ,keyProperty = "seq", resultType = Long.class)
    Long save(Todo todo);

    @Select("SELECT * FROM TODO WHERE isDone = false ORDER BY priority DESC, deadline OFFSET #{offset} LIMIT #{limit}" )
    List<Todo> selectAll(long offset, int limit);

    @Select("SELECT * FROM TODO WHERE seq = #{seq}")
    Optional<Todo> select(Long seq);

    @Update("UPDATE TODO SET title = #{title}, contents = #{contents}, deadline = #{deadline}, priority = #{priority}, isDone = #{isDone}, hasDeadline = #{hasDeadline} WHERE seq = #{seq}")
    Long update(Todo todo);

    @Delete("DELETE FROM TODO WHERE seq = #{seq}")
    void delete(Long seq);

    @Select("SELECT * FROM TODO WHERE isDone = true ORDER BY deadline OFFSET #{offset} LIMIT #{limit}" )
    List<Todo> selectAllDone(long offset, int limit);
}
