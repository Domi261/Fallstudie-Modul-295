package ch.bbcag.backend.todolist.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    public Item insert(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Methode zum Finden von Items anhand eines Namens
    @GetMapping
    public List<Item> findItems(@RequestParam String name) {
        return itemRepository.findByNameContains(name);
    }
}
