package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void buildLinesReturnsExpectedValues() {
        assertEquals(List.of("i = 1", "i = 2", "i = 3"), Main.buildLines(3));
    }

    @Test
    void buildLinesWithZeroReturnsEmptyList() {
        assertTrue(Main.buildLines(0).isEmpty());
    }
}