package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Product item1 = new Book(1, "Harry Potter", 100, "J.K.Rowling");
    private Product item2 = new Book(2, "The Colour of Magic", 200, "Terry Pratchett");
    private Product item3 = new TShirt(3, "Blue", 500, "Nike");
    private Product item4 = new TShirt(4, "Red", 1000, "Reebok");

    @BeforeEach
    void setUp(){
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
    }

    @Test
    void shouldRemoveByExistId() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = {
                item2,
                item3,
                item4
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdNotExist(){
        assertThrows(NotFoundException.class, () -> repository.removeById(42));
    }
}
