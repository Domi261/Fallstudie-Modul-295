package ch.bbcag.backend.todolist.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("SELECT i FROM Item i WHERE i.name LIKE CONCAT('%', :name, '%')")
    List<Item> findByName(String name);

    // Die Methode ist gleich wie die obere, aber Spring generiert die Query basierend auf dem Methodennamen
    List<Item> findByNameContains(String name);
}
