package com.friedNote.friedNote_backend.domain.ingredientGroup.domain.service;

import com.friedNote.friedNote_backend.common.annotation.DomainService;
import com.friedNote.friedNote_backend.domain.ingredientGroup.domain.entity.IngredientGroup;
import com.friedNote.friedNote_backend.domain.ingredientGroup.domain.repository.IngredientGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DomainService
@RequiredArgsConstructor
@Transactional
public class IngredientGroupQueryService {

    private final IngredientGroupRepository ingredientGroupRepository;


    /**
     * 같은 기능을 하는 메서드 2개라 정리 필요
     */

    public List<IngredientGroup> findIngredientGroupByRecipeId(Long recipeId) {
        return ingredientGroupRepository.findIngredientGroupByRecipeId(recipeId);
    }

    public List<IngredientGroup> findByRecipeId(Long recipeId) {
        return ingredientGroupRepository.findByRecipeId(recipeId);
    }

    public IngredientGroup findIngredientGroupById(Long ingredientGroupId) {
        return ingredientGroupRepository.findIngredientGroupById(ingredientGroupId);
    }
}
