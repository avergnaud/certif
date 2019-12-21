```
Benchmark                                      Mode  Cnt    Score    Error  Units
OcaStreamPerformanceTest.mapReduceV1          thrpt   25  296,850 ±  9,907  ops/s
OcaStreamPerformanceTest.mapReduceV2          thrpt   25  310,771 ± 17,112  ops/s
OcaStreamPerformanceTest.oneActorOfEachAgeV1  thrpt   25  327,312 ± 15,753  ops/s
OcaStreamPerformanceTest.oneActorOfEachAgeV2  thrpt   25  316,095 ± 16,384  ops/s
```

OcaStream.java :
```
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
     *
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
```