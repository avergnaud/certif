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
     *
     * @param decade
     * @return
     */
    public List<Integer> uniqueAgesListV1(final Decade decade) {

        Set<Integer> ageSet = new HashSet<>();
        for (Movie movie : decade.getMovies()) {
            for (Actor actor : movie.getActors()) {
                ageSet.add(actor.getAge());
            }
        }
        List<Integer> uniqueAgesList = new ArrayList<>(ageSet);
        Collections.sort(uniqueAgesList);
        return uniqueAgesList;
    }

    /**
     * V2
     *
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
     *
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
     *
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
     * Retourne un Predicate de T qui :
     *   applique une function de T, (obtient le résultat)
     *   et qui retourne true uniquement s'il n'a pas déjà rencontré le résultat
     *
     * Closure :
     * "A closure is a special kind of object that combines two things: a function,
     * and the environment in which that function was created. The environment
     * consists of any local variables that were in-scope at the time that the
     * closure was created"
     * @param keyExtractor : une function de T
     * @param <T>
     * @return un Predicate de T
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        /* La méthode distinctByKey n'est appelée qu'une seule fois, à l'init du stream */
        /*final*/ Set<Object> seen = ConcurrentHashMap.newKeySet();

        /*
        Le body de la lambda expression est appelé à chaque itération dans le filter()
        autorisé parce-que seen est "effectively final"
        "Set.add returns true if this set did not already contain the specified element"
        */
        return t -> seen.add(keyExtractor.apply(t));
    }

}

