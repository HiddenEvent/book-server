package me.ricky.boardserver.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.PostDTO;
import me.ricky.boardserver.dto.request.PostSearchRequest;
import me.ricky.boardserver.service.PostSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PostSearchController.BASE_URI)
@Slf4j
@RequiredArgsConstructor
public class PostSearchController {
    public static final String BASE_URI = "/post/search";

    private final PostSearchService postSearchService;

    @PostMapping
    public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = postSearchService.getPosts(postSearchRequest);
        return new PostSearchResponse(postDTOList);
    }
    @GetMapping
    public PostSearchResponse searchByTagName(@RequestParam("tagName") String tagName) {
        List<PostDTO> postDTOList = postSearchService.getPostsByTagName(tagName);
        return new PostSearchResponse(postDTOList);
    }
    @Getter
    @AllArgsConstructor
    private static class PostSearchResponse {
        private List<PostDTO> postDTOList;
    }
}
