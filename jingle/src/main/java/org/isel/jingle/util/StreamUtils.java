package org.isel.jingle.util;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class StreamUtils {

    public static <T,U,R> Stream<R> merge(Stream<T> seq1, Stream<U> seq2, BiPredicate<T,U> pred, BiFunction<T,U,R> transf, U defaultVal) {
        // TODO
        return null;
    }

}
