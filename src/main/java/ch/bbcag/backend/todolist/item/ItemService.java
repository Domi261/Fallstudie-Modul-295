package ch.bbcag.backend.todolist.item;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Integer id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByNameAndTagName(String name, String tagName) {
        return itemRepository.findByNameAndTagName(name, tagName);
    }

    public List<Item> findByTagName(String tagName) {
        return itemRepository.findByTagName(tagName);
    }

    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Item changingItem, Integer id) {
        Item existingItem = this.findById(id);
        mergeItems(existingItem, changingItem);
        return itemRepository.save(existingItem);
    }

    public void deleteById(Integer id) {
        itemRepository.deleteById(id);
    }


    private void mergeItems(Item existingItem, Item changingItem) {
        if (changingItem.getName() != null) {
            existingItem.setName(changingItem.getName());
        }
        if (changingItem.getDescription() != null) {
            existingItem.setDescription(changingItem.getDescription());
        }
        if (changingItem.getDoneAt() != null) {
            existingItem.setDoneAt(changingItem.getDoneAt());
        }
        if (changingItem.getDeletedAt() != null) {
            existingItem.setDeletedAt(changingItem.getDeletedAt());
        }
        if (changingItem.getPerson() != null) {
            existingItem.setPerson(changingItem.getPerson());
        }
        if (changingItem.getLinkedTags() != null) {
            existingItem.setLinkedTags(changingItem.getLinkedTags());
        }
    }
}
