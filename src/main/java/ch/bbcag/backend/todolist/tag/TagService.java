package ch.bbcag.backend.todolist.tag;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findById(Integer id) {
        return tagRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag insert(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag changingTag, Integer id) {
        Tag existingTag = this.findById(id);
        mergeTags(existingTag, changingTag);
        return tagRepository.save(existingTag);
    }

    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }


    private void mergeTags(Tag existingTag, Tag changingTag) {
        if (changingTag.getName() != null) {
            existingTag.setName(changingTag.getName());
        }
    }
}
