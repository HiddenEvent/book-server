package me.ricky.boardserver.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.aop.LoginCheck;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.UserDTO;
import me.ricky.boardserver.dto.response.CommonResponse;
import me.ricky.boardserver.service.PostService;
import me.ricky.boardserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(PostController.BASE_URI)
@Slf4j
public class PostController {
    public static final String BASE_URI = "/posts";
    private final UserService userService;
    private final PostService postService;


    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }
    @PostMapping
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> register(String accountId,
                                                            @RequestBody PostDTO postDTO) {
        postService.register(accountId, postDTO);
        return ResponseEntity.ok(new CommonResponse<>(HttpStatus.CREATED, "CREATED", "게시글 등록 성공", postDTO));
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDTO>>> getMyPosts(String accountId) {
        UserDTO userInfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyPosts(userInfo.getId());
        return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK, "OK", "내가 작성한 게시글 조회 성공", postDTOList));
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> update(String accountId,
                                                               @PathVariable("postId") int postId,
                                                               @RequestBody PostRequest postRequest) {
        UserDTO userInfo = userService.getUserInfo(accountId);
        PostDTO postDTO = PostDTO.builder()
                .id(postId)
                .name(postRequest.getName())
                .contents(postRequest.getContents())
                .views(postRequest.getViews())
                .categoryId(postRequest.getCategoryId())
                .userId(userInfo.getId())
                .fileId(postRequest.getFileId())
                .updateTime(new Date())
                .build();
        postService.update(postDTO);
        return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK, "OK", "게시글 수정 성공", postDTO));
    }
    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDeleteRequest>> delete(String accountId,
                                                                    @PathVariable("postId") int postId,
                                                                    @RequestBody PostDeleteRequest request) {
        UserDTO userInfo = userService.getUserInfo(accountId);
        postService.delete(userInfo.getId(), postId);
        return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK, "OK", "게시글 삭제 성공", request));
    }


    @Getter
    @AllArgsConstructor
    private static class PostResponse {
        private List<PostDTO> postDTOS;
    }

    @Setter
    @Getter
    private static class PostRequest {
        private String name;
        private String contents;
        private int views;
        private int categoryId;
        private int userId;
        private int fileId;
        private Date updatedTime;
    }

    @Setter
    @Getter
    private static class PostDeleteRequest {
        private int id;
        private int accountId;
    }

}
