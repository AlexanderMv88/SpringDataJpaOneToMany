package org.springDataJpaOneToMany.bidirectional.repository;

import org.springDataJpaOneToMany.bidirectional.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String title);
}
