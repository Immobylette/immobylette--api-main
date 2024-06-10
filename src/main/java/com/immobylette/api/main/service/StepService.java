package com.immobylette.api.main.service;

import com.immobylette.api.main.domain.StateTypeEnum;
import com.immobylette.api.main.dto.PhotoDto;
import com.immobylette.api.main.dto.StepDto;
import com.immobylette.api.main.entity.Element;
import com.immobylette.api.main.entity.Inventory;
import com.immobylette.api.main.entity.StateType;
import com.immobylette.api.main.entity.Step;
import com.immobylette.api.main.exception.ElementNotFoundException;
import com.immobylette.api.main.exception.GCPStorageException;
import com.immobylette.api.main.exception.InventoryNotFoundException;
import com.immobylette.api.main.exception.PhotosAndDescriptionsSizeException;
import com.immobylette.api.main.mapper.StepMapper;
import com.immobylette.api.main.repository.ElementRepository;
import com.immobylette.api.main.repository.InventoryRepository;
import com.immobylette.api.main.repository.StateTypeRepository;
import com.immobylette.api.main.repository.StepRepository;
import com.immobylette.api.main.resource.PhotoResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StepService {
    private InventoryRepository inventoryRepository;
    private ElementRepository elementRepository;
    private StepRepository stepRepository;
    private StateTypeRepository stateTypeRepository;
    private final PhotoResource photoResource;

    private final StepMapper stepMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public void createStep(UUID inventoryId, UUID elementId, StepDto stepDto, List<MultipartFile> photos) throws InventoryNotFoundException, ElementNotFoundException, GCPStorageException {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        Element element = elementRepository.findByInventoryId(elementId, inventoryId).orElseThrow(()
                -> new ElementNotFoundException(elementId, inventoryId));

        StateType stateType = stateTypeRepository.findByLabel(stepDto.getState().getName());

        List<PhotoDto> photoList = new ArrayList<>();

        if(photos.size() != stepDto.getPhotosDescriptions().size()) {
            throw new PhotosAndDescriptionsSizeException();
        }

        Iterator<MultipartFile> photoIterator = photos.iterator();
        Iterator<String> descriptionIterator = stepDto.getPhotosDescriptions().iterator();

        while (photoIterator.hasNext() && descriptionIterator.hasNext()) {
            PhotoDto photo = new PhotoDto(descriptionIterator.next(), photoIterator.next());
            photoList.add(photo);
        }

        UUID folderId = photoResource.uploadPhotos(photoList);

        Step step = stepMapper.fromStepSentDto(stepDto, stateType, inventory, element, folderId);

        stepRepository.save(step);
    }

    public void createSameStep(UUID inventoryId, UUID elementId) throws InventoryNotFoundException, ElementNotFoundException, GCPStorageException {
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new InventoryNotFoundException(inventoryId));

        Element element = elementRepository.findByInventoryId(elementId, inventoryId).orElseThrow(()
                -> new ElementNotFoundException(elementId, inventoryId));

        Step step = stepRepository.findFirstByElementIdOrderByInventoryInventoryDateDesc(elementId);

        if(step == null) {
            StateType stateType = stateTypeRepository.findByLabel(StateTypeEnum.NEW.getName());
            step = new Step(null, "", "", element.getPhotoFolder(), stateType, inventory, element);
        } else {
            step.setId(null);
        }

        step.setInventory(inventory);

        entityManager.detach(step);

        stepRepository.save(step);
    }
}
