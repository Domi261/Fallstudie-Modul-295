package ch.bbcag.backend.todolist.item;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = ItemController.PATH)
public class ItemController {
    public static final String PATH = "/items";

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        try {
            Item item = itemService.findById(id);
            return ResponseEntity.ok(ItemMapper.toResponseDTO(item));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody ItemRequestDTO newItemDTO) {
        try {
            Item newItem = ItemMapper.fromRequestDTO(newItemDTO);
            Item savedItem = itemService.insert(newItem);
            return ResponseEntity.status(201).body(ItemMapper.toResponseDTO(savedItem));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Item could not be created");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            itemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item was not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> findItems(@RequestParam(required = false) String name) {
        List<Item> items = StringUtils.isNotBlank(name)
                ? itemService.findByName(name)
                : itemService.findAll();

        return ResponseEntity.ok(items.stream()
                .map(ItemMapper::toResponseDTO)
                .toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ItemRequestDTO updateItemDTO, @PathVariable Integer id) {
        try {
            Item updateItem = ItemMapper.fromRequestDTO(updateItemDTO);
            Item savedItem = itemService.update(updateItem, id);
            return ResponseEntity.status(201).body(ItemMapper.toResponseDTO(savedItem));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Item could not be created");
        }
    }
}
