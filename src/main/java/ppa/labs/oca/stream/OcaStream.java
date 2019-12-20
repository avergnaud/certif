package ppa.labs.oca.stream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ppa.labs.oca.stream.model.Actor;
import ppa.labs.oca.stream.model.Decade;
import ppa.labs.oca.stream.model.Movie;

public class OcaStream {

    /**
     * V1
     * @param decade
     * @return
     */
    public List<Integer> uniqueAgesListV1(final Decade decade) {

        Set<Integer> ageSet = new HashSet<>();
        for(Movie movie : decade.getMovies()) {
            for(Actor actor : movie.getActors()) {
                ageSet.add(actor.getAge());
            }
        }
        List<Integer> uniqueAgesList = new ArrayList<>(ageSet);
        Collections.sort(uniqueAgesList);
        return uniqueAgesList;
    }

    /**
     * V2
     * @param decade
     * @return
     */
    public List<Integer> uniqueAgesListV2(final Decade decade) {

        return decade.getMovies().stream()
                .flatMap(movie -> movie.getActors().stream())
                .map(Actor::getAge)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * collectToSet
     * @param decade
     * @return
     */
    public Set<Actor> oneActorOfEachAgeV1(final Decade decade) {

        return decade.getMovies().stream()
                .flatMap(movie -> movie.getActors().stream())
                .filter(distinctByKey(Actor::getAge))
                .collect(Collectors.toSet());

    }

    /**
     * collectToMapThenSet
     * @param decade
     * @return
     */
    public Set<Actor> oneActorOfEachAgeV2(final Decade decade) {

        Map<Integer, Actor> someActorsByAge = decade.getMovies().stream()
                .flatMap(movie -> movie.getActors().stream())
                .collect(Collectors.toMap(
                        Actor::getAge,
                        Function.identity(),
                        (existing, replacement) -> existing));
        return new HashSet<>(someActorsByAge.values());
    }

    /**
     * util
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}

