package com.example.Record_Shop.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {

    Properties properties = new Properties();

    public DBConfig() throws IOException {
        String configFilePath= "/home/jezdavisuk/Northcoders/projects/Record-Shop-API/config/db.properties";

        File ConfigFile = new File(configFilePath);

        FileInputStream configFileReader = new FileInputStream(ConfigFile);
        properties.load(configFileReader);
        configFileReader.close();
    }

    public String getUrl(){
        return properties.getProperty("db.url");
    }

    public String getUsername(){
        return properties.getProperty("db.username");
    }

    public String getPassword(){
        return properties.getProperty("db.password");
    }
}
