package me.ricky.boardserver.service;

import me.ricky.boardserver.dto.CategoryDTO;

public interface CategoryService {
    void register(String accountId, CategoryDTO categoryDTO);
    void update( CategoryDTO categoryDTO);
    void delete(int categoryId);
}
