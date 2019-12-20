package ppa.labs.oca.stream.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Decade {

    private int year;
    private List<Movie> movies;
    public static final int MIN_AGE = 18;//sorry Mr Weinstein
    public static final int MAX_AGE = 70;

    public Decade(int nbMovies, int nbActors, int year) {

        this.year = year;
        this.movies = new ArrayList<>();

        for (int j = 0; j < nbMovies; j++) {
            List<Actor> lsActors = new ArrayList<>();
            for (int i = 0; i < nbActors; i++) {
                int rdInt = ThreadLocalRandom.current().nextInt(MIN_AGE, MAX_AGE);
                Actor actor = new Actor(
                        "actorFirstName" + rdInt,
                        "actorLastName" + rdInt,
                        rdInt
                );
                lsActors.add(actor);
            }
            this.movies.add(new Movie("title" + j, lsActors));
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }
}

