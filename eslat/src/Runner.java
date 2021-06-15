import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        FileInputStream fileInputStream = null;
        try {
            Path path = Paths.get(".\\IRIS1.txt");
            String content = new String(Files.readAllBytes(path), charset);

            fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            ArrayList<String> listBuffer ;
            while(reader.ready()) {

                String line = reader.readLine();
                System.out.println(line );
                listBuffer = runner.parseIntsAndFloats(line);

                System.out.println(listBuffer.toArray());
                content = content.replaceAll("foo", "bar");
            }
            Files.write(path, content.getBytes(charset));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
