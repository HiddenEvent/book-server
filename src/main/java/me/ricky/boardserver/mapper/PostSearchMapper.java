package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.request.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostSearchMapper {

    List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
}
