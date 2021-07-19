import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomTransactionCreator {

    public static void main(String[] str)
    {
        String st1 = "haftom-moharam";
        String st2 = "masjed-seyed zahir";
        String st3 = "masjed-seyed zahed";
        String st4 = "masjed-seyed tayeb";
        String st5 = "zahir mirdamad";
        String st6 = "zahed mirdamad";
        String st7 = "tayeb mirdamad";
        String itemList[] = {st1,st2,st3,st4,st5,st6,st7};
        int numberOfTransaction = 1000000;
        String speprator = ",";
        Random rand = new Random();
        Random rand2 = new Random();
        String row = "";
        String output="";
        for(int i=0;i<numberOfTransaction; i++)
        {
            System.out.print("***** I = "+i+1);
            int n = rand.nextInt(7);

            n++;
            int dis = 7-n;
            row = "";
            for(int j=0;j<n;j++)
            {
                int m = rand2.nextInt(7);

                if(j==n-1)
                {
                    row = row+itemList[m];
                }
                else
                {
                    row = row+itemList[m]+",";
                }
            }

            for(int j=0;j<dis;j++)
            {

                row = row+",";
            }
            System.out.println("n= "+n+" row = "+ row);
            output = output+ row+"\n";
        }
        File outputFile =  new File(".\\outputEclat.csv");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFile);
            fileOutputStream.write(output.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(output);
    }
}
