import ca.pfv.spmf.algorithms.graph_mining.tseqminer.EclatAlgo;
import ca.pfv.spmf.algorithms.frequentpatterns.eclat.AlgoDEclat;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {
    private ArrayList<String> parseIntsAndFloats(String raw) {

        ArrayList<String> listBuffer = new ArrayList<String>();

        Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");

        Matcher m = p.matcher(raw);

        while (m.find()) {
            listBuffer.add(m.group());
        }

        return listBuffer;
    }

    public static void main(String[] str)  {
        Runner runner = new Runner();
        System.out.println("hello");
        File file = new File(".\\IRIS.txt");
        File outputFile =  new File(".\\IRIS2.txt");
        File outputFile2 =  new File(".\\IRIS3.txt");
        HashMap<String, String> allNumbers = new HashMap<String, String>();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2 =null;
        Path path = Paths.get(".\\IRIS.txt");
        Charset charset = StandardCharsets.UTF_8;
        String outputString = "";
        try {
            fileOutputStream = new FileOutputStream(outputFile);
            fileOutputStream2 = new FileOutputStream(outputFile2);
            fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            int counter=1;
            while(reader.ready()) {
                String line = reader.readLine();
                System.out.println(line );
                Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");
                Matcher m = p.matcher(line);
                String changeDot;
                while (m.find()) {
                    changeDot = m.group().replaceAll("\\.",",");
                    allNumbers.put(changeDot,changeDot);
                    //    allNumbers.putIfAbsent(m.group(),String.valueOf(counter++));
                }
                //     System.out.println(allNumbers.size());
            }
            System.out.println(allNumbers.size());
            Object[] allnumbersString  =  allNumbers.keySet().toArray();
            counter = 1 ;
            String content = new String(Files.readAllBytes(path), charset);
            // content = content.replaceAll(".", "%2E");
            //  content = content.contains(".") ? content.replace(".", "%2E") : content;
            content =  content.replaceAll("\\.",",");
            for (int i= 0; i < allnumbersString.length ;i++)
            {
                content = content.replaceAll(String.valueOf(allnumbersString[i]), ""+counter+"");

                //    Files.write(path, content.getBytes(charset));

                outputString = outputString +  counter+ " "+ String.valueOf(allnumbersString[i])+"\n";
                counter ++;

            }
            System.out.println("***************** ");
            System.out.println(content);
            System.out.print(outputString);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            fileOutputStream2.write(outputString.getBytes());
            fileOutputStream2.close();

            TransactionDatabase transactionDatabase = new TransactionDatabase();
            transactionDatabase.loadFile(".\\IRIS2.txt");
            String outputAlgotithm = ".\\outputEclat.txt";
            AlgoDEclat algoDEclat = new AlgoDEclat();
            algoDEclat.runAlgorithm(outputAlgotithm,transactionDatabase,0.01,false);
            System.out.println("********");
            System.out.println(outputAlgotithm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
