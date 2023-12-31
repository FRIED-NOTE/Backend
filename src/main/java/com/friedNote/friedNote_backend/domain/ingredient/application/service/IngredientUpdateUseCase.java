package com.friedNote.friedNote_backend.domain.ingredient.application.service;

import com.friedNote.friedNote_backend.common.annotation.DomainService;
import com.friedNote.friedNote_backend.domain.ingredient.application.dto.request.IngredientRequest;
import com.friedNote.friedNote_backend.domain.ingredient.domain.entity.Ingredient;
import com.friedNote.friedNote_backend.domain.ingredient.domain.service.IngredientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@RequiredArgsConstructor
@Transactional
public class IngredientUpdateUseCase {

    private final IngredientQueryService ingredientQueryService;

    public void updateIngredient(IngredientRequest.IngredientUpdateRequest ingredientUpdateRequest) {
        String ingredientName = ingredientUpdateRequest.getIngredientName();
        String ingredientAmount = ingredientUpdateRequest.getIngredientAmount();
        String ingredientUnit = ingredientUpdateRequest.getIngredientUnit();
        Long ingredientGroupId = ingredientUpdateRequest.getIngredientGroupId();

        List<Ingredient> ingredientList = ingredientQueryService.findByIngredientGroupId(ingredientGroupId);
        ingredientList.forEach(ingredient -> {
            ingredient.updateIngredientInfo(ingredientName, ingredientAmount, ingredientUnit);
        });
    }
}
