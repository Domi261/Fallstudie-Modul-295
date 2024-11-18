package ch.bbcag.backend.todolist.tag;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(TagController.PATH)
public class TagController {
    public static final String PATH = "/tags";

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        try {
            Tag tag = tagService.findById(id);
            return ResponseEntity.ok(TagMapper.toResponseDTO(tag));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag was not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody TagRequestDTO newTagDTO) {
        try {
            Tag newTag = TagMapper.fromRequestDTO(newTagDTO);
            Tag savedTag = tagService.insert(newTag);
            return ResponseEntity.status(201).body(TagMapper.toResponseDTO(savedTag));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag could not be created");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            tagService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag was not found");
        }
    }

    @GetMapping
    public ResponseEntity<?> findTags(@RequestParam(required = false) String name) {
        List<Tag> tags = name != null
                ? tagService.findByName(name)
                : tagService.findAll();

        return ResponseEntity.ok(tags.stream()
                .map(TagMapper::toResponseDTO)
                .toList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TagRequestDTO updateTagDTO, @PathVariable Integer id) {
        try {
            Tag updateTag = TagMapper.fromRequestDTO(updateTagDTO);
            Tag savedTag = tagService.update(updateTag, id);
            return ResponseEntity.status(201).body(TagMapper.toResponseDTO(savedTag));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag could not be created");
        }
    }
}
