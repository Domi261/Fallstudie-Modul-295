package ch.bbcag.backend.todolist.person;

import ch.bbcag.backend.todolist.item.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public static PersonResponseDTO toResponseDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setId(person.getId());
        personResponseDTO.setUsername(person.getUsername());

        // Hinzufügen der Item-IDs zu PersonResponseDTO
        if (person.getItems() != null) {
            List<Integer> itemIds = person.getItems()
                    .stream()
                    .map(Item::getId)
                    .collect(Collectors.toList());
            personResponseDTO.setItemIds(itemIds);
        }

        return personResponseDTO;
    }

    public static Person fromRequestDTO(PersonRequestDTO personRequestDTO) {
        Person person = new Person();
        person.setUsername(personRequestDTO.getUsername());
        return person;
    }
}
