package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by anatarajan on 7/10/16.
 */
public class Configuration {
    private Properties properties;
    private String propertyFile;

    private static Configuration configuration;

    public static class KeyWithValueTypeT<T>{
        private String keyName;

        public KeyWithValueTypeT(String name){
            this.keyName = name;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }
    }

    protected Configuration(){
        properties = new Properties();
    }

    public static Configuration getInstance(){
        if(configuration == null){
            configuration = new Configuration();
        }
        return configuration;
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public void loadConfiguration(InputStream inputStream){
        try {
            properties.load(inputStream);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadFromFile(){
        InputStream fileInputStream = Configuration.class.getClassLoader().getResourceAsStream(propertyFile);
        loadConfiguration(fileInputStream);
    }

    public Integer get(KeyWithValueTypeT<Integer> key, Integer defaultValue){
        String value = properties.getProperty(key.getKeyName());
        if(value == null){
            return defaultValue;
        } else {
            return Integer.valueOf(value);
        }
    }

    public Boolean get(KeyWithValueTypeT<Boolean> key, boolean defaultValue){
        String value = properties.getProperty(key.getKeyName());
        if(value == null){
            return defaultValue;
        } else {
            return Boolean.valueOf(value);
        }
    }

    public String get(KeyWithValueTypeT<String> key, String defaultValue){
        String value = properties.getProperty(key.getKeyName());
        if(value == null){
            return defaultValue;
        } else {
            return String.valueOf(value);
        }
    }
}
