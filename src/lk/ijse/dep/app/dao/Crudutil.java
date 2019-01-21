package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crudutil {
    public static <T> T execute(String sql,Object... params) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        int paramsCount = getParametersCount(sql);

        if(params.length!=paramsCount){
            throw new RuntimeException("Parameter count mismatch error");
        }

        for (int i = 0; i < paramsCount; i++) {
            pst.setObject(i+1,params[i]);

        }
        return sql.startsWith("SELECT")? (T) pst.executeQuery() :(T)(Integer)pst.executeUpdate();

    }


    private static int getParametersCount(String sql){
        return sql.concat(" ").split("[?]").length-1;

    }}
