import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionMaker {

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
                    row = row +"D"+rs.getString("DeviceId")+",";
                    plateNumb1=plateNumb;
                 //   System.out.println("row = "+row);
                   }
                else
                {
              //      System.out.println(plateNumb+"=!"+plateNumb1);
                    row = row+"\n";
                  //  System.out.println("PlatNum = "+rs.getString("PlateNo"));
               //    row = row+"\n"+"------plateNumb"+plateNumb;
                    row = row +"D"+rs.getString("DeviceId")+",";

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
            int deviceCount = 11;
            HashMap<String, String> allNumbers = new HashMap();
           File file = new File(".\\TrafficOut.csv");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            boolean var13 = true;
            String outputAlgotithm="";
            String newRow="";
            Pattern p;
            Matcher m;
            while(reader.ready()) {
                newRow="";
                String line = reader.readLine();
              //  System.out.println(line);
                 p = Pattern.compile("[0-9]*\\.?[0-9]+");
                 m = p.matcher(line);
                int mSize=0;
                int mSize2=0;
                while(m.find())
                {
                    mSize++;
                }
                m = p.matcher(line);
                System.out.println("mSize = "+mSize);
                while(m.find()) {
                    mSize2++;
                    if(mSize2 < mSize) {
                        newRow = newRow + "D" + m.group() + ",";
                    }
                    else
                    {
                        newRow = newRow + "D" + m.group();
                    }

                }
                for(int i =0; i<deviceCount-mSize;i++)
                {
                    newRow = newRow+",";
                }
                System.out.println(newRow);
                outputAlgotithm =  outputAlgotithm+"\n"+ newRow;
            }


            System.out.println(outputAlgotithm);

            File outputFile1 =  new File(".\\TrafficOut2.csv");
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream2 = new FileOutputStream(outputFile1);
                fileOutputStream2.write(outputAlgotithm.getBytes());
                fileOutputStream2.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
