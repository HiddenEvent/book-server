package me.ricky.boardserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.CategoryDTO;
import me.ricky.boardserver.mapper.CategoryMapper;
import me.ricky.boardserver.service.CategoryService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if (accountId == null) throw new RuntimeException("로그인이 필요합니다.");
        categoryMapper.register(categoryDTO);
    }
    @Override
    public void update(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            log.error("카테고리 수정 정보가 없습니다. {}", categoryDTO);
            throw new RuntimeException("카테고리 수정 정보가 없습니다.");
        }
        categoryMapper.update(categoryDTO);
    }

    @Override
    public void delete(int categoryId) {
        if (categoryId == 0) {
            log.error("카테고리 삭제 정보가 없습니다. {}", categoryId);
            throw new RuntimeException("카테고리 삭제 정보가 없습니다.");
        }
        categoryMapper.delete(categoryId);

    }
}
