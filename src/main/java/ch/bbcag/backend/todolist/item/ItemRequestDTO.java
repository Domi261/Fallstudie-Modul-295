package ch.bbcag.backend.todolist.item;

import java.time.LocalDateTime;
import java.util.Objects;

public class ItemRequestDTO {

    private String name;
    private String description;
    private Integer personId;
    private LocalDateTime deletedAt;
    private LocalDateTime doneAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemRequestDTO that = (ItemRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(personId, that.personId) && Objects.equals(deletedAt, that.deletedAt) && Objects.equals(doneAt, that.doneAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, personId, deletedAt, doneAt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }
}
