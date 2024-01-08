package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    int register(PostDTO postDTO);
    List<PostDTO> selectMyPosts(int accountId);
    void update(PostDTO postDTO);
    void delete(int userId, int postId);
}
