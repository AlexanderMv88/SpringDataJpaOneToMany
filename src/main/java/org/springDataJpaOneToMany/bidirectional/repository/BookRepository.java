package org.springDataJpaOneToMany.bidirectional.repository;

import org.springDataJpaOneToMany.bidirectional.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findOneByName(String title);
}
