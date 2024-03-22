package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://ujfvl0mmodwzy9lr:xSB64tmsNc845jLYSQYd@bfiwdwudnud7h5tmihsz-mysql.services.clever-cloud.com:3306/bfiwdwudnud7h5tmihsz";
            String user = "ujfvl0mmodwzy9lr";
            String password = "xSB64tmsNc845jLYSQYd";
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Me conecte perfectamente!!")
            ;
        }catch (ClassNotFoundException error){
            System.out.println("Error >> DRIVER NO INSTALADO "+ error.getMessage());
        }catch (SQLException error){
            System.out.println("Error >> error al conectar con la base de datos "+ error.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if (objConnection != null) objConnection.close();
            System.out.println("Se finaliizo la conexion con exito");
        }catch (SQLException error){
            System.out.println("Error: "+error.getMessage());
        }
    }
}
