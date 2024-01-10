package me.ricky.boardserver.service;

import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> getPosts(PostSearchRequest postSearchRequest);
}
