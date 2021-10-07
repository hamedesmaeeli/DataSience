import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class ConnectionMaker2 {

    /* Use jdbc connection url to access sql server.*/
    public static void connectSqlServerUseURL()
    {
        // Build sql server jdbc connection url use sql server account authentication.
        //    String sqlServerConnectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;user=sa;password=008632";
        String sqlServerConnectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=dbETCC;integratedSecurity=true;";
        // Use windows authentication.
        //String sqlServerConnectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;integratedSecurity=true;";
        // Declare the JDBC objects.
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String row="";
        try {
            // Load jdbc driver class.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Get connection.
            dbConn = DriverManager.getConnection(sqlServerConnectionUrl);
            // Execute sql and return data result.
            String SQL = "SELECT  [Id]\n" +
                    "      ,[DeviceId]\n" +
                    "      ,[ActionDateTime]\n" +
                    "      ,[PassDate]\n" +
                    "      ,[PassTime]\n" +
                    "      ,[VehicleTypeId]\n" +
                    "      ,[PlateTypeId]\n" +
                    "      ,[PlateNo]\n" +
                    "      ,[RFIDTagNo]\n" +
                    "      ,[Allowed]\n" +
                    "      ,[CrimeId]\n" +
                    "      ,[LaneNumber]\n" +
                    "      ,[Direction]\n" +
                    "      ,[Speed]\n" +
                    "      ,[AverageSpeed]\n" +
                    "      ,[DelayTime]\n" +
                    "      ,[ImageAccuracy]\n" +
                    "      ,[HasImage]\n" +
                    "      ,[PassDateTime]\n" +
                    "      ,[PlateString]\n" +
                    "      ,[OstandariId]\n" +
                    "  FROM [dbETCC].[dbo].[tblTraffic] where id >390749544 and id <390849544 and PassDate=13990402\n" +
                    "  ORDER by PlateNo";
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(SQL);
            // Loop the data result and display the data.
            int plateNumb = 0;
            int plateNumb1 = 0;

            if(rs.next())
            {
                System.out.println("*********plateNumb = "+rs.getString("DeviceId"));
                plateNumb1 = Integer.valueOf(rs.getString("PlateNo"));
                row= rs.getString("DeviceId");

                //  System.out.println("*********row = "+row);

            }
            while (rs.next()) {
                plateNumb = Integer.valueOf(rs.getString("PlateNo"));
                //      System.out.println("*********plateNumb = "+plateNumb);
                if (plateNumb == plateNumb1)
                {
                    //     System.out.println(plateNumb+"=="+plateNumb1);
                    row = row +rs.getString("DeviceId")+",";
                    plateNumb1=plateNumb;
                    //   System.out.println("row = "+row);
                }
                else
                {
                    //      System.out.println(plateNumb+"=!"+plateNumb1);
                    row = row+"\n";
                    //  System.out.println("PlatNum = "+rs.getString("PlateNo"));
                    //    row = row+"\n"+"------plateNumb"+plateNumb;
                    row = row + rs.getString("DeviceId")+",";

                    //      row = row + rs.getString("DeviceId")+",";
                    // System.out.println(row);
                    plateNumb1=plateNumb;
                }


                //  System.out.println(rs.getString("PlateNo"));

            }
            File outputFile =  new File(".\\TrafficOut.csv");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(outputFile);
                fileOutputStream.write(row.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(row);
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (rs != null)
            {
                try {
                    rs.close();
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (stmt != null)
            {
                try {
                    stmt.close();
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }

            if (dbConn != null)
            {
                try {
                    dbConn.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void main(String str[])
    {
        ConnectionMaker.connectSqlServerUseURL();
    }
}
