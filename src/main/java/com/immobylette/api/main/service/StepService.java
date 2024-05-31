package com.immobylette.api.main.service;

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
}
