package task1.orgin;

public class Test {
	public static void main(String[] args) {
		Movie movie = new Movie("动画",1);
		Rental rental = new Rental(movie,20);
		Customer customer = new Customer("sdd");
		customer.addRental(rental);
		System.out.println(customer.statement());
	}
}
