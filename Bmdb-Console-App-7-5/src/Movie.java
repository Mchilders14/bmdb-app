
public class Movie {

	private int id, year;
	private String title, rating, director;
	
	public Movie() {
		// Default Constructor
	}

	public Movie(int id, int year, String title, String rating, String director) {
		super();
		this.id = id;
		this.year = year;
		this.title = title;
		this.rating = rating;
		this.director = director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", year=" + year + ", title=" + title + ", rating=" + rating + ", director="
				+ director + "]";
	}
	
	
}
