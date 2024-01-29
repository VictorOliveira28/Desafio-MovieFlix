package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query("SELECT m FROM Movie m JOIN FETCH m.genre WHERE m.genre = :genre ORDER BY m.title ASC")
	Page<Movie> findByGenreId(Genre genre, Pageable pageable);
	
	@Query("SELECT m FROM Movie m JOIN FETCH m.genre")
	Page<Movie> searchAll(PageRequest pageRequest);
	
}
