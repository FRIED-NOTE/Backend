package com.friedNote.friedNote_backend.domain.inventory.application.service;

import com.friedNote.friedNote_backend.common.annotation.UseCase;
import com.friedNote.friedNote_backend.common.util.UserUtils;
import com.friedNote.friedNote_backend.domain.inventory.application.dto.response.InventoryResponse;
import com.friedNote.friedNote_backend.domain.inventory.application.mapper.InventoryMapper;
import com.friedNote.friedNote_backend.domain.inventory.domain.entity.Inventory;
import com.friedNote.friedNote_backend.domain.inventory.domain.service.InventoryQueryService;
import com.friedNote.friedNote_backend.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UseCase
@RequiredArgsConstructor
@Transactional
public class InventoryGetUseCase {

    private final InventoryQueryService inventoryQueryService;

    private final UserUtils userUtils;

    public List<InventoryResponse.InventoryInfoResponse> getInventoryList() {
        User user = userUtils.getUser();
        Long userId = user.getId();
        List<Inventory> inventoryList = inventoryQueryService.findByUserId(userId);

        List<InventoryResponse.InventoryInfoResponse> inventoryInfoResponseList
                = inventoryList.stream()
                .map(inventory -> {
                    InventoryResponse.InventoryInfoResponse inventoryInfoResponse
                            = InventoryMapper.mapToInventoryInfo(inventory);
                    return inventoryInfoResponse;
                }).collect(toList());
        return inventoryInfoResponseList;
    }
    public List<InventoryResponse.InventoryInfoResponse> getInventoryListByExpirationDate() {
        User user = userUtils.getUser();
        Long userId = user.getId();
        List<Inventory> inventoryList = inventoryQueryService.findByUserId(userId);
        List<InventoryResponse.InventoryInfoResponse> inventoryInfoResponseList = new ArrayList<>();
        inventoryList.forEach(inventory -> {
            LocalDate expirationDate = inventory.getExpirationDate();
            LocalDate nowDate = LocalDate.now();
            if(ChronoUnit.DAYS.between(nowDate, expirationDate) <= 7
                    && ChronoUnit.DAYS.between(nowDate,expirationDate) >= 0){
                inventoryInfoResponseList.add(InventoryMapper.mapToInventoryInfo(inventory));
            }
        });
        return inventoryInfoResponseList;
    }

    public List<InventoryResponse.InventoryTagInfoResponse> getTagInfo() {
        User user = userUtils.getUser();
        Long userId = user.getId();
        List<Inventory> inventoryList = inventoryQueryService.findByUserId(userId);

        if(inventoryList.isEmpty()){
            return null;
        }

        List<InventoryResponse.InventoryTagInfoResponse> inventoryTagInfoResponseList = inventoryList.stream()
                .filter(inventory -> ChronoUnit.DAYS.between(LocalDate.now(), inventory.getExpirationDate()) >= 0)
                .sorted(Comparator.comparing(inventory ->
                        ChronoUnit.DAYS.between(LocalDate.now(), inventory.getExpirationDate())))
                .map(inventory -> {
                    InventoryResponse.InventoryTagInfoResponse inventoryTagInfoResponse
                            = InventoryMapper.mapToInventoryTagInfo(inventory);
                    return inventoryTagInfoResponse;
                })
                .limit(6)
                .collect(toList());

        return inventoryTagInfoResponseList;
    }

}
