package me.ricky.boardserver.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ricky.boardserver.dto.CategoryDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSearchRequest {
    private int id;
    private String name;
    private String contents;
    private int views;
    private int categoryId;
    private int userId;
    private CategoryDTO.SortStatus sortStatus;
}
