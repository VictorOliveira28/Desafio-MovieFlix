package com.devsuperior.movieflix.projections;

import com.devsuperior.movieflix.dto.GenreDTO;

public interface MovieDetailsProjection {
	
	Long getId();
	String getTitle();
	String getSubTitle();
	Integer getYear();
	String getImgUrl();
	String getSynopsis();
	GenreDTO getGenre();	 
	    
}
