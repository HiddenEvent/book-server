package me.ricky.boardserver.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.aop.LoginCheck;
import me.ricky.boardserver.dto.CategoryDTO;
import me.ricky.boardserver.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URI)
@Slf4j
public class CategoryController {
    public static final String BASE_URI = "/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void register(String accountId, @RequestBody CategoryDTO categoryDTO) {
        categoryService.register(accountId, categoryDTO);
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void update(String accountId,
                       @PathVariable int categoryId,
                       @RequestBody CategoryRequest categoryRequest) {

        CategoryDTO categoryDTO = new CategoryDTO(categoryId, categoryRequest.getName(), CategoryDTO.SortStatus.NEWEST, 10, 1);
        categoryService.update(categoryDTO);
    }

    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    public void delete(String accountId,
                       @PathVariable int categoryId) {
        categoryService.delete(categoryId);
    }

    @Getter
    @Setter
    private static class CategoryRequest {
        private int id;
        private String name;
    }


}
