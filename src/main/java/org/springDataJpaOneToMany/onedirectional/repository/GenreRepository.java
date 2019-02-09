package org.springDataJpaOneToMany.onedirectional.repository;

import org.springDataJpaOneToMany.onedirectional.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String title);
}
