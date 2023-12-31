package com.friedNote.friedNote_backend.domain.bookmark.application.service;

import com.friedNote.friedNote_backend.common.annotation.UseCase;
import com.friedNote.friedNote_backend.common.util.UserUtils;
import com.friedNote.friedNote_backend.domain.bookmark.application.dto.request.BookmarkRequest;
import com.friedNote.friedNote_backend.domain.bookmark.application.mapper.BookmarkMapper;
import com.friedNote.friedNote_backend.domain.bookmark.domain.entity.Bookmark;
import com.friedNote.friedNote_backend.domain.bookmark.domain.service.BookmarkDeleteService;
import com.friedNote.friedNote_backend.domain.bookmark.domain.service.BookmarkQueryService;
import com.friedNote.friedNote_backend.domain.bookmark.domain.service.BookmarkSaveService;
import com.friedNote.friedNote_backend.domain.recipe.domain.entity.Recipe;
import com.friedNote.friedNote_backend.domain.recipe.domain.service.RecipeQueryService;
import com.friedNote.friedNote_backend.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class BookmarkCreateUseCase {

    private final UserUtils userUtils;
    private final BookmarkSaveService bookmarkSaveService;
    private final RecipeQueryService recipeQueryService;

    private final BookmarkQueryService bookmarkQueryService;
    private final BookmarkDeleteService bookmarkDeleteService;

    public void createBookmark(BookmarkRequest.BookmarkCreateRequest bookmarkCreateRequest) {
        Long userId = bookmarkCreateRequest.getUserId();
        User user = userUtils.getUser();

        Long recipeId = bookmarkCreateRequest.getRecipeId();
        Recipe recipe = recipeQueryService.findRecipeById(recipeId);

        Bookmark bookmark = BookmarkMapper.mapToBookmark(user, recipe);

        if(!bookmarkQueryService.existsByUserIdAndRecipeId(userId, recipeId)){
            bookmarkSaveService.saveBookmark(bookmark);
        } else {
            bookmarkDeleteService.deleteBookmark(userId, recipeId);
        }
    }


}
