package gamomat.classes;

import gamomat.interfaces.IConfigurationReader;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * configuration reader class, does everything we need to retrieve configs
 */
public class ConfigurationReader implements IConfigurationReader {
    Configuration config;

    public ConfigurationReader(File configFile) {
        Parameters params = new Parameters();

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setFile(configFile));
        try
        {
            this.config = builder.getConfiguration();
        }
        catch(ConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getCsvStringMultArray(String prop) {
        String[] rows = this.config.getString(prop).split(";");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (String row : rows) {
            ArrayList<String> elementArrayList = new ArrayList<>();
            Collections.addAll(elementArrayList, row.split(","));
            result.add(elementArrayList);
        }
        return result;
    }

    @Override
    public ArrayList<String> getCsvStringArray(String prop) {
        String[] elements = this.config.getString(prop).split(",");
        return new ArrayList<>(Arrays.asList(elements));
    }

    @Override
    public String getString(String prop) {
        return this.config.getString(prop);
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCsvIntMultArray(String prop) {
        String[] rows = this.config.getString(prop).split(";");
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (String row : rows) {
            ArrayList<Integer> elementArrayList = new ArrayList<>();
            for (String element : row.split(",")){
                elementArrayList.add(Integer.parseInt(element));
            }
            result.add(elementArrayList);
        }
        return result;
    }

    @Override
    public int getInt(String prop) {
        return this.config.getInt(prop);
    }
}
