package com.privatemoviecollection.dal.db;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionManager {
    private static final String PROP_FILE = "Data/DbCredentials/Config.properties";
    private SQLServerDataSource ds;

    public ConnectionManager() throws IOException {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(PROP_FILE));
        String server = databaseProperties.getProperty("Server");
        String database = databaseProperties.getProperty("Database");
        String user = databaseProperties.getProperty("User");
        String password = databaseProperties.getProperty("Password");
        ds = new SQLServerDataSource();
        ds.setDatabaseName(database);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setServerName(server);
        ds.setTrustServerCertificate(true);
    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}