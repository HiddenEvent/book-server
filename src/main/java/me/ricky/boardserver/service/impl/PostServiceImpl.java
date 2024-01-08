package me.ricky.boardserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.UserDTO;
import me.ricky.boardserver.mapper.PostMapper;
import me.ricky.boardserver.mapper.UserProfileMapper;
import me.ricky.boardserver.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final UserProfileMapper userProfileMapper;

    public PostServiceImpl(PostMapper postMapper, UserProfileMapper userProfileMapper) {
        this.postMapper = postMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public void register(String accountId, PostDTO postDTO) {
        UserDTO userProfile = userProfileMapper.getUserProfile(accountId);
        if (userProfile == null) {
            log.error("유저가 존재하지 않습니다. {}", accountId);
            throw new RuntimeException("게시글 등록 에러");
        }
        postDTO.setUserId(userProfile.getId());
        postDTO.setCreatedTime(new Date());
        postMapper.register(postDTO);
    }

    @Override
    public List<PostDTO> getMyPosts(int accountId) {
        List<PostDTO> postDTOList = postMapper.selectMyPosts(accountId);
        return postDTOList;
    }

    @Override
    public void update(PostDTO postDTO) {
        if (postDTO == null) {
            log.error("게시글 수정 정보가 없습니다. {}", postDTO);
            throw new RuntimeException("게시글 수정 에러");
        }
        postMapper.update(postDTO);
    }

    @Override
    public void delete(int userId, int postId) {
        if (postId == 0) {
            log.error("게시글 삭제 정보가 없습니다. {}", postId);
            throw new RuntimeException("게시글 삭제 에러");
        }
        postMapper.delete(userId, postId);
    }
}