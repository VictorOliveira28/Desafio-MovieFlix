package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	GenreRepository repository;

	public List<GenreDTO> findAll(String name) {
		
		List<Genre> genres = repository.searchByName(name);		
		
		List<GenreDTO> dto = genres.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
		
		return dto;		
	
	}

	
}
