package me.ricky.boardserver.service;

import me.ricky.boardserver.dto.CommentDTO;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.TagDTO;

import java.util.List;

public interface PostService {
    void register(String accountId, PostDTO postDTO);
    List<PostDTO> getMyPosts(int accountId);
    void update(PostDTO postDTO);
    void delete(int userId, int postId);

    void registerComment(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);

    void deleteComment(int userId, int commentId);
    void registerTag(TagDTO tagDTO);

    void updateTag(TagDTO tagDTO);
    void deleteTag(int userId, int tagId);
}
