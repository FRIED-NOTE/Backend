package com.friedNote.friedNote_backend.domain.recipeBook.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RecipeBookRequest {

    @Getter
    @NoArgsConstructor
    public static class RecipeBookCreateRequest {

        private String title;
        private String subtitle;
        private Long userId;
        private boolean publicityStatus;

        @Builder
        public RecipeBookCreateRequest(String title, String subtitle, Long userId, boolean publicityStatus) {
            this.title = title;
            this.subtitle = subtitle;
            this.userId = userId;
            this.publicityStatus = publicityStatus;
        }
    }



}
