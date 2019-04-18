package org.isel.jingle.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class StreamUtilsTest {


    private final List<String> seq1 = Arrays.asList("isel", "ola", "dup", "super", "jingle");
    private final List<Integer> seq2Asc = Arrays.asList(4,5,6,7);
    private final List<Integer> seq2AscDupl = Arrays.asList(4,5,6,7,4,5,6,7);
    private final List<Integer> seq2Desc = Arrays.asList(7,6,5,4);
    private final List<Integer> seq2DescDupl = Arrays.asList(7,6,5,4,7,6,5,4);
    private final List<String> expected = Arrays.asList("isel4", "ola0", "dup0", "super5", "jingle6");

    @Test
    public void shouldMergeSequencesWithoutDuplicatesOnSeq2Ascending() {
        final List<String> merged = merge(seq2Asc);

        assertEquals(expected, merged);
    }

    @Test
    public void shouldMergeSequencesWithoutDuplicatesOnSeq2AscendingWithDuplicates() {
        final List<String> merged = merge(seq2AscDupl);
        assertEquals(expected, merged);
    }

    @Test
    public void shouldMergeSequencesWithDuplicatesOnSeq2Descending() {
        final List<String> merged = merge(seq2Desc);

        assertEquals(expected, merged);
    }

    @Test
    public void shouldMergeSequencesWithDuplicatesOnSeq2DescendingWithDuplicates() {
        final List<String> merged = merge(seq2DescDupl);

        assertEquals(expected, merged);
    }

    @Test
    public void shouldMergeSequencesWithDuplicatesOnBothSequences() {
        final List<String> merged = merge(
                Stream.concat(seq1.stream(), seq1.stream()).collect(Collectors.toList()),
                seq2DescDupl);

        assertEquals(Stream.concat(expected.stream(), expected.stream()).collect(toList()), merged);
    }


    private List<String> merge(List<Integer> seq2) {
        return StreamUtils.merge(seq1.stream(), seq2.stream(), (str, nr) -> str.length() == nr, (str, nr) -> str + nr, 0).collect(toList());
    }

    private List<String> merge(List<String> seq1, List<Integer> seq2) {
        return StreamUtils.merge(seq1.stream(), seq2.stream(), (str, nr) -> str.length() == nr, (str, nr) -> str + nr, 0).collect(toList());
    }
}