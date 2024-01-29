package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
    private AuthService authService;
	
		
	/*public ReviewDTO insert(ReviewDTO dto) {
		
	    Review entity = new Review();
	    entity.setId(dto.getId());
	    entity.setText(dto.getText());	    
		
	    Movie movie = new Movie();
	    movie.setId(dto.getMovieId());
	    entity.setMovie(movie);
	    
	    if (dto.getUserId() != null) {
	        User user = new User();
	        user.setId(dto.getUserId());
	        user.setName(dto.getUserName());
	        user.setEmail(dto.getUserEmail());
	        entity.setUser(user);	       
	    }	    

	    entity = repository.save(entity);

	    return new ReviewDTO(entity);
	}*/
	
	@Transactional
    public ReviewDTO insert(ReviewDTO dto) {

        User entity =  authService.authenticated();
        Review review = new Review();
        
        try {
        	
            review.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
            review.setText(dto.getText());
            review.setUser(entity);
            repository.save(review);
        }
        
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return new ReviewDTO(review);
    }
	
}
