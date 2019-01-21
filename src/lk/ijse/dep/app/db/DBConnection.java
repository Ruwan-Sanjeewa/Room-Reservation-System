package lk.ijse.dep.app.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;
    private static DBConnection dbConnection;

    private DBConnection() throws Exception{
        File file = new File("resource/application.properties");
        FileReader fileReader = new FileReader(file);
        Properties dbProp = new Properties();
        dbProp.load(fileReader);
        String ip = dbProp.getProperty("ip");
        String port = dbProp.getProperty("port");
        String database = dbProp.getProperty("database");
        String username = dbProp.getProperty("username");
        String password = dbProp.getProperty("password");

        String sql="jdbc:mysql://"+ip+":"+port+"/"+database;
        connection = DriverManager.getConnection(sql, username, password);

    }



    public static DBConnection getInstance() throws Exception{
        if(dbConnection==null){
            return  dbConnection=new DBConnection();
        }
        return dbConnection;
    }


    public Connection getConnection()   {
        return connection;
    }
}
