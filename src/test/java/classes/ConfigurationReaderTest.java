package classes;

import gamomat.classes.ConfigurationReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class ConfigurationReaderTest {
    public static ConfigurationReader config;
    @BeforeAll
    static void setUp() {
        ConfigurationReaderTest.config = new ConfigurationReader(new File("slotMachine.properties"));
    }

    @Test
    void getCsvStringMultArray() {
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList("AAA","0.1")));
        expected.add(new ArrayList<>(Arrays.asList("BBB","0.15")));
        expected.add(new ArrayList<>(Arrays.asList("CCC","0.2")));


        Assertions.assertEquals(expected,config.getCsvStringMultArray("winConditions"));
    }

    @Test
    void getCsvStringArray() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("reel1", "reel2", "reel3"));
        Assertions.assertEquals(expected, config.getCsvStringArray("reels"));
    }

    @Test
    void getString() {
        String expected = "ACACACABCCCABCBCACBA";
        Assertions.assertEquals(expected, config.getString("reel1"));
    }

    @Test
    void getCsvIntMultArray() {

        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        expected.add(new ArrayList<>(Arrays.asList(3, 4, 5)));
        expected.add(new ArrayList<>(Arrays.asList(6, 7, 8)));
        expected.add(new ArrayList<>(Arrays.asList(0, 4, 8)));
        expected.add(new ArrayList<>(Arrays.asList(6, 4, 2)));

        Assertions.assertEquals(expected, config.getCsvIntMultArray("winLines"));
    }

    @Test
    void getInt() {
        int expected = 3;
        Assertions.assertEquals(expected, config.getInt("reelRows"));
    }
}