package org.springDataJpaOneToMany.onedirectional.repository;

import org.springDataJpaOneToMany.onedirectional.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findOneByName(String title);
}
