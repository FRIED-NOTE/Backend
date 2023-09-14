package com.friedNote.friedNote_backend.domain.ingredient.domain.entity;

import com.friedNote.friedNote_backend.common.domain.BaseTimeEntity;
import com.friedNote.friedNote_backend.domain.ingredientGroup.domain.entity.IngredientGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    private String ingredientName;
    private Long ingredientAmount;
    private String ingredientUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredientGroup_id")
    private IngredientGroup ingredientGroup;

    @Builder
    public Ingredient(String ingredientName, Long ingredientAmount, String ingredientUnit, IngredientGroup ingredientGroup) {
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
        this.ingredientUnit = ingredientUnit;
        this.ingredientGroup = ingredientGroup;
    }
}