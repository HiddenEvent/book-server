package me.ricky.boardserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.CommentDTO;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.TagDTO;
import me.ricky.boardserver.dto.UserDTO;
import me.ricky.boardserver.mapper.CommentMapper;
import me.ricky.boardserver.mapper.PostMapper;
import me.ricky.boardserver.mapper.TagMapper;
import me.ricky.boardserver.mapper.UserProfileMapper;
import me.ricky.boardserver.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final UserProfileMapper userProfileMapper;
    private final CommentMapper commentMapper;
    private final TagMapper tagMapper;

    public PostServiceImpl(PostMapper postMapper, UserProfileMapper userProfileMapper, CommentMapper commentMapper, TagMapper tagMapper) {
        this.postMapper = postMapper;
        this.userProfileMapper = userProfileMapper;
        this.commentMapper = commentMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public void register(String accountId, PostDTO postDTO) {
        UserDTO userProfile = userProfileMapper.getUserProfile(accountId);
        if (userProfile == null) {
            log.error("유저가 존재하지 않습니다. {}", accountId);
            throw new RuntimeException("게시글 등록 에러");
        }
        postDTO.setUserId(userProfile.getId());
        postDTO.setCreateTime(new Date());
        postMapper.register(postDTO);

        if (postDTO.getTagDTOList() == null) return;
        postDTO.getTagDTOList().forEach(tagDTO -> {
            tagDTO.setPostId(postDTO.getId());
            tagMapper.register(tagDTO);
            tagMapper.createPostTag(tagDTO.getId(), postDTO.getId());
        });
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

    @Override
    public void registerComment(CommentDTO commentDTO) {
        if (commentDTO.getPostId() == 0) {
            log.error("게시글 Id 유효하지 않음 {}", commentDTO);
            throw new RuntimeException("댓글 등록 에러");
        }
        commentMapper.register(commentDTO);
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        if (commentDTO == null) {
            log.error("댓글 수정 정보가 없습니다. {}", commentDTO);
            throw new RuntimeException("댓글 수정 에러");
        }
        commentMapper.update(commentDTO);
    }

    @Override
    public void deleteComment(int userId, int commentId) {
        if (userId == 0 || commentId == 0) {
            log.error("댓글 삭제 정보가 없습니다. {}, {}", userId, commentId);
            throw new RuntimeException("댓글 삭제 에러");
        }
        commentMapper.delete(commentId);
    }

    @Override
    public void registerTag(TagDTO tagDTO) {
        if (tagDTO == null) {
            log.error("태그 등록 정보가 없습니다. {}", tagDTO);
            throw new RuntimeException("태그 등록 에러");
        }
        tagMapper.register(tagDTO);
    }

    @Override
    public void updateTag(TagDTO tagDTO) {
        if (tagDTO == null) {
            log.error("태그 수정 정보가 없습니다. {}", tagDTO);
            throw new RuntimeException("태그 수정 에러");
        }
        tagMapper.update(tagDTO);
    }

    @Override
    public void deleteTag(int userId, int tagId) {
        if (userId == 0 || tagId == 0) {
            log.error("태그 삭제 정보가 없습니다. {}, {}", userId, tagId);
            throw new RuntimeException("태그 삭제 에러");
        }
        tagMapper.delete(tagId);
    }
}
