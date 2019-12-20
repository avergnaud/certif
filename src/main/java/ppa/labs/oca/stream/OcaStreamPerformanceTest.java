package ppa.labs.oca.stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ppa.labs.oca.stream.model.Actor;
import ppa.labs.oca.stream.model.Decade;

import java.util.List;
import java.util.Set;

public class OcaStreamPerformanceTest {

    static final Logger logger = LoggerFactory.getLogger(OcaStreamPerformanceTest.class);
    static final Decade decade = new Decade(100, 1000, 2010);
    static final OcaStream ocaStream = new OcaStream();

    @Benchmark
    public void mapReduceV1() {
        List<Integer> retour = ocaStream.uniqueAgesListV1(decade);
        logger.debug("mapReduceV1 " + retour.size());
    }

    @Benchmark
    public void mapReduceV2() {
        List<Integer> retour = ocaStream.uniqueAgesListV2(decade);
        logger.debug("mapReduceV2 " + retour.size());
    }

    @Benchmark
    public void oneActorOfEachAgeV1() {
        Set<Actor> retour = ocaStream.oneActorOfEachAgeV1(decade);
        logger.debug("oneActorOfEachAgeV1 " + retour.size());
    }

    @Benchmark
    public void oneActorOfEachAgeV2() {
        Set<Actor> retour = ocaStream.oneActorOfEachAgeV2(decade);
        logger.debug("oneActorOfEachAgeV2 " + retour.size());
    }
}
