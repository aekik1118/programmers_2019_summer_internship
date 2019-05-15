package com.programmers.summerinternship.demo.repository;

import com.programmers.summerinternship.demo.model.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TodoRepository {
    @Insert("INSERT INTO TODO (seq, title, contents, endAt, priority) VALUES (#{seq}, #{title}, #{contents}, #{endAt}, #{priority});")
    @SelectKey(statement = "SELECT nextval('seq_todo')", before = true ,keyProperty = "seq", resultType = Long.class)
    Long save(Todo todo);
}
