package me.ricky.boardserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.request.PostSearchRequest;
import me.ricky.boardserver.mapper.PostSearchMapper;
import me.ricky.boardserver.service.PostSearchService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PostSearchServiceImpl implements PostSearchService {
    private final PostSearchMapper postSearchMapper;

    public PostSearchServiceImpl(PostSearchMapper postSearchMapper) {
        this.postSearchMapper = postSearchMapper;
    }
    @Cacheable(value = "getPosts", key = "'getPosts' + #postSearchRequest.getName() + #postSearchRequest.getCategoryId()")
    @Override
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postSearchMapper.selectPosts(postSearchRequest);
        } catch (Exception e) {
            log.error("selectPosts 실패 : {}", e.getMessage());
        }

        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostsByTagName(String tagName) {
        List<PostDTO> postDTOList = postSearchMapper.getPostByTagName(tagName);
        return postDTOList;
    }
}
