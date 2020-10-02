package ro.sda.boot.intro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.boot.intro.entities.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
