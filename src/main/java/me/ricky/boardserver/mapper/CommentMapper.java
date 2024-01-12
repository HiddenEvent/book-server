package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int register(CommentDTO commentDTO);
    void update(CommentDTO commentDTO);
    void delete(int commentId);

}
