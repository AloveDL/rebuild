package task1.changed;


public class Rental {
	private Movie _movie;
	private int _dayRented;

	public Rental(Movie movie, int daysRented){
		_movie= movie;
		_dayRented = daysRented;
	}

	public int getDayRented(){
		return _dayRented;
	}

	public Movie getMovie(){
		return _movie;
	}

	public double getCharge(){
		double result = 0;
		switch (getMovie().getPriceCode()){
			case Movie.REGULAR:
				result += 2;
				if (getDayRented() > 2) {
					result += (getDayRented()-2)*1.5;
				}
				break;
			case Movie.NEW_RELEASE:
				result += getDayRented()*3;
				break;
			case Movie.CHILDRENS:
				result += 1.5;
				if(getDayRented()>3) {
					result += (getDayRented()-3)*1.5;
				}
				break;
			default:
		}
		return result;
	}
}
