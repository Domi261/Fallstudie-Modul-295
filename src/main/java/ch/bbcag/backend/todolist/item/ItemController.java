package ch.bbcag.backend.todolist.item;

import ch.bbcag.backend.todolist.person.Person;
import ch.bbcag.backend.todolist.person.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;

    // Konstruktor-Injektion
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

    @PostMapping("/add")
    public Item insert(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping("/all") // all
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping
    public List<Item> findItemsByName(@RequestParam(value = "name", required = false) String name) {
        if (name != null && !name.isBlank()) {
            return itemRepository.findByName(name);
        }
        return itemRepository.findAll();
    }
}