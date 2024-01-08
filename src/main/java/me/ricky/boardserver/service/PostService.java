package me.ricky.boardserver.service;

import me.ricky.boardserver.dto.PostDTO;

import java.util.List;

public interface PostService {
    void register(String accountId, PostDTO postDTO);
    List<PostDTO> getMyPosts(int accountId);
    void update(PostDTO postDTO);
    void delete(int userId, int postId);
}
