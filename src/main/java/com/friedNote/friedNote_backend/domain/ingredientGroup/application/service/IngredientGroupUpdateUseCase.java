package com.friedNote.friedNote_backend.domain.ingredientGroup.application.service;

import com.friedNote.friedNote_backend.common.annotation.DomainService;
import com.friedNote.friedNote_backend.domain.ingredient.application.service.IngredientUpdateUseCase;
import com.friedNote.friedNote_backend.domain.ingredientGroup.application.dto.request.IngredientGroupRequest;
import com.friedNote.friedNote_backend.domain.ingredientGroup.domain.entity.IngredientGroup;
import com.friedNote.friedNote_backend.domain.ingredientGroup.domain.service.IngredientGroupQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class IngredientGroupUpdateUseCase {

    private final IngredientGroupQueryService ingredientGroupQueryService;
    private final IngredientUpdateUseCase ingredientUpdateUseCase;
    public void updateIngredientGroup(IngredientGroupRequest.IngredientGroupUpdateRequest ingredientGroupUpdateRequest) {
        String groupName = ingredientGroupUpdateRequest.getGroupName();
        Long recipeId = ingredientGroupUpdateRequest.getRecipeId();

        List<IngredientGroup> ingredientGroupList = ingredientGroupQueryService.findByRecipeId(recipeId);
        ingredientGroupList.forEach(ingredientGroup -> {
            ingredientGroup.updateIngredientGroupName(groupName);
        });
        ingredientGroupUpdateRequest.getIngredientUpdateRequestList().forEach(ingredientUpdateRequest -> {
            ingredientUpdateUseCase.updateIngredient(ingredientUpdateRequest);
        });
    }
}
