package vRealizeServiceBroker.catalog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vRealizeServiceBroker.catalog.model.Item;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InMemoryCatalogRepositoryTests {
    @Test
    void doesItReturnAllItemsWith_findAll_with_MockData() {
        var repository = new InMemoryCatalogRepository();
        assertSame(FakeData.CATALOG, repository.findAll());
    }

    @Test
    void isItSavingNewItemProperly(){
        var repository = new InMemoryCatalogRepository();
        var item = new Item().setId("abcdef-eeee-54d-31gh").setName("test-3").setDescription("");
        var savedItem = repository.save(item);
        assertEquals(savedItem.getId(), item.getId());
        assertEquals(savedItem.getName(), item.getName());
        assertEquals(savedItem.getDescription(), item.getDescription());
    }
    @Test
    void deleteExistingItem(){
        var repository = new InMemoryCatalogRepository();
        var item = repository.findByID("abcdef-eeee-54d-31gh").get();
        repository.delete(item);
        assertTrue(repository.findByID("abcdef-eeee-54d-31gh").isEmpty());
    }


    @Test
    void isItUpdatingExistingItemProperly_AndNotAddingNewItem(){
        var repository = new InMemoryCatalogRepository();
        var item = FakeData.CATALOG.get(0).setDescription("I have a description");
        var savedItem = repository.save(item);
        assertEquals(savedItem.getId(), item.getId());
        assertEquals(savedItem.getName(), item.getName());
        assertEquals(savedItem.getDescription(), item.getDescription());
      var repoSize = ((Collection<?>) repository.findAll()).size();
       assertEquals(repoSize,2);

    }
}
