package com.maxcheung.arrayjumper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolutionTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        Solution.playGame(5, 3, "0 0 0 0");
    }

    @Test
    public void shouldWinGame() {
        assertTrue(Solution.playGame(5, 3, "0 0 0 0 0"));
        assertTrue(Solution.playGame(6, 5, "0 0 0 1 1 1"));
        assertTrue(Solution.playGame(14, 9, "0 0 0 1 1 1 1 0 1 1 0 1 0 1"));
    }

    @Test
    public void shouldReturnWinGameNotPossible() {
        assertFalse(Solution.playGame(6, 3, "0 0 0 1 1 1"));
        assertFalse(Solution.playGame(3, 1, "0 1 0"));

    }

}