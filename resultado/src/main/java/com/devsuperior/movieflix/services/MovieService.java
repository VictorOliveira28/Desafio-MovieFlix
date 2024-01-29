package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	MovieRepository repository;
	
	@Autowired
	GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {

		Movie entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		return new MovieDetailsDTO(entity);

	}

	
	@Transactional(readOnly = true)
	public Page<MovieDetailsDTO> findByGenre(List<Long> genreId, Pageable pageable) {
		
		List<Genre> genres = genreRepository.findAllById(genreId);

	    if (genres.isEmpty()) return repository.searchAll(PageRequest.of(0, 5, Sort.by("title"))).map(MovieDetailsDTO::new);     
	     
	    Genre genre = genres.get(0);
	    Page<Movie> movies = repository.findByGenreId(genre, pageable);

	    return movies.map(MovieDetailsDTO::new);
	}		

}
