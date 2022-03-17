package gamomat.interfaces;

import java.util.ArrayList;

/**
 * configuration reader interface, subset of methods we need for our implementation
 */
public interface IConfigurationReader {
    /**
     * returns a multidimensional string array from a single string
     * currently enforces "," and ";" as default delimiter can be easily overloaded
     * for custom ones
     * @param prop property name
     * @return String[][]
     */
    ArrayList<ArrayList<String>> getCsvStringMultArray(String prop);

    /**
     * returns a string array from a single string
     * currently enforces "," as default delimiter can be easily overloaded
     * for custom ones
     * @param prop property name
     * @return String[]
     */
    ArrayList<String> getCsvStringArray(String prop);

    /**
     * returns a string from our configs
     * @param prop property name
     * @return String
     */
    String getString(String prop);

    /**
     * returns a multidimensional string array from a single string
     * currently enforces "," and ";" as default delimiter can be easily overloaded
     * for custom ones
     * @param prop property name
     * @return int[][]
     */
    ArrayList<ArrayList<Integer>> getCsvIntMultArray(String prop);

    /**
     * returns an int from our configs
     * @param prop property name
     * @return int
     */
    int getInt(String prop);
}
