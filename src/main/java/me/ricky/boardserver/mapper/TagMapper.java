package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {
    int register(TagDTO tagDTO);
    void update(TagDTO tagDTO);
    void delete(int postId);
    void createPostTag(Integer tagId, Integer postId);
}
