package ppa.labs.oca.stream;

import org.junit.jupiter.api.Test;
import ppa.labs.oca.stream.model.Actor;
import ppa.labs.oca.stream.model.Decade;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class OcaStreamTest {

    /* for big numbers !*/
    List<Integer> expected = IntStream.range(Decade.MIN_AGE, Decade.MAX_AGE)
            .boxed()
            .collect(Collectors.toList());

    Decade decade = new Decade(100, 1000, 2010);

    OcaStream ocaStream = new OcaStream();

    @Test
    void mapReduceV1() {
        List<Integer> retour = ocaStream.uniqueAgesListV1(decade);
        assertTrue(expected.equals(retour));
    }

    @Test
    void mapReduceV2() {
        List<Integer> retour = ocaStream.uniqueAgesListV2(decade);
        assertTrue(expected.equals(retour));
    }

    @Test
    void oneActorOfEachAgeV1() {
        Set<Actor> retour = ocaStream.oneActorOfEachAgeV1(decade);
        assertEquals(Decade.MAX_AGE - Decade.MIN_AGE, retour.size());
    }

    @Test

    void oneActorOfEachAgeV2() {
        Set<Actor> retour = ocaStream.oneActorOfEachAgeV2(decade);
        assertEquals(Decade.MAX_AGE - Decade.MIN_AGE, retour.size());
    }
}