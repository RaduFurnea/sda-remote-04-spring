package ro.sda.boot.intro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.boot.intro.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
