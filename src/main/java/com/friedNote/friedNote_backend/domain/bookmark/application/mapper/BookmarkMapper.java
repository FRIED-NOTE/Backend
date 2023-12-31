package com.friedNote.friedNote_backend.domain.bookmark.application.mapper;

import com.friedNote.friedNote_backend.domain.bookmark.application.dto.response.BookmarkResponse;
import com.friedNote.friedNote_backend.domain.bookmark.domain.entity.Bookmark;
import com.friedNote.friedNote_backend.domain.recipe.domain.entity.Recipe;
import com.friedNote.friedNote_backend.domain.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkMapper {

    public static Bookmark mapToBookmark(User user, Recipe recipe) {
        return Bookmark.builder()
                .user(user)
                .recipe(recipe)
                .build();
    }

    /**
     * TO DO
     * 수정 필요
     */
    public static BookmarkResponse.BookmarkInfoResponse mapToBookmarkInfoResponse(Bookmark bookmark) {
        return BookmarkResponse.BookmarkInfoResponse.builder()
                .recipeId(bookmark.getRecipe().getId())
                .isBookMark(true)
                .build();
    }

    public static BookmarkResponse.BookmarkCountResponse mapToBookMarkCountResponse(Long count) {
        return BookmarkResponse.BookmarkCountResponse.builder()
                .count(count)
                .build();
    }
}
