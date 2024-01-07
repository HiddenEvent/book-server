package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.CategoryDTO;

public interface CategoryMapper {
    int register(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(int categoryId);
}
