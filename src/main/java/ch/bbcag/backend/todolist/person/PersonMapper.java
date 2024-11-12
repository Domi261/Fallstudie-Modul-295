package ch.bbcag.backend.todolist.person;

import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public static PersonResponseDTO toResponseDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();

        personResponseDTO.setId(person.getId());
        personResponseDTO.setUsername(person.getUsername());

        return personResponseDTO;
    }

    public static Person fromRequestDTO(PersonRequestDTO personRequestDTO) {
        Person person = new Person();
        person.setUsername(personRequestDTO.getUsername());
        return person;
    }

}
