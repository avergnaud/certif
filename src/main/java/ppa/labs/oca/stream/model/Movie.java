package ppa.labs.oca.stream.model;

import java.util.List;

public class Movie {

	private String title;
	private List<Actor> actors;

	public Movie(final String title, final List<Actor> actors) {
		this.title = title;
		this.actors = actors;
	}

	public List<Actor> getActors() {
		return actors;
	}
}


