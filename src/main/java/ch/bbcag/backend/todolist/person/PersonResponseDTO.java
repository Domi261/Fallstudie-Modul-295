package ch.bbcag.backend.todolist.person;

import java.util.List;
import java.util.Objects;

public final class PersonResponseDTO extends PersonRequestDTO {
    private Integer id;
    private String username;
    private List<Integer> itemIds;

    // Getter und Setter für itemIds
    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }

    // Getter und Setter für id und username
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonResponseDTO personResponseDTO)) {
            return false;
        }
        return super.equals(obj) && id.equals(personResponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "PersonResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", itemIds=" + itemIds +
                '}';
    }
}
