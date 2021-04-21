import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVtoTXT {
    public static void main(String[] args) throws IOException {
        String rute = "C:/Users/Eric/Desktop/traducciones/fromCSV/";

        File f = new File(rute);
        String[] listFiles = f.list();
        assert listFiles != null;
        for (String file : listFiles) {
            if (!file.equals("out.csv")) {
                new File(rute + file).delete();
            }
        }
        try {
            Map<String, List<String>> lists = new HashMap<>();
            List<String> lines = Files.readAllLines(Paths.get(rute + "out.csv"), Charset.defaultCharset());
            int counter = lines.get(0).split(";").length;
            for (int i = 1; i < counter; i++) {
                List<String> values = new ArrayList<>();
                String nameFile = "";
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        //System.out.println(line);
                        String[] a = line.split(";");
                        //System.out.println(a[0] + "  " + a[i]);
                        if (a[0].equals("KEY")) {
                            nameFile = a[i];
                        } else {
                            //System.out.println(a.length);
                            //System.out.println(a[i-1]);
                            try {
                                values.add(a[0] + "=>" + a[i]);
                            } catch (Exception e) {
                                values.add(a[0] + "=> ");
                            }
                        }
                    }
                }
                lists.put(nameFile, values);

            }
            for (Map.Entry<String, List<String>> entry : lists.entrySet()) {

                File fout = new File(rute + entry.getKey());
                try {
                    FileOutputStream fos = new FileOutputStream(fout);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    boolean posible = false;
                    //System.out.println(entry.getKey());
                    if (entry.getKey().contains(".php")) {
                        posible = true;
                    }
                    for (String s : entry.getValue()) {
                        if (posible && s.contains("php")) {
                            bw.write(s);
                            bw.newLine();
                        } else if (!posible && !s.contains("php")) {
                            bw.write(s);
                            bw.newLine();
                        }


                    }
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}