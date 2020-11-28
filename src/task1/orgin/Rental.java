package task1.orgin;

import task1.orgin.Movie;

public class Rental {
	private Movie _movie;
	private int _dayRented;

	public Rental(Movie movie,int daysRented){
		_movie= movie;
		_dayRented = daysRented;
	}

	public int getDayRented(){
		return _dayRented;
	}

	public Movie getMovie(){
		return _movie;
	}
}
