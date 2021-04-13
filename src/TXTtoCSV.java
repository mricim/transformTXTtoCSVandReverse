import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TXTtoCSV {
    public static void main(String[] args) throws Exception {
        String rute = "C:/Users/Eric/Desktop/traducciones/fromTXT/";
        LinkedHashSet<String> hashSetKeys = new LinkedHashSet<>();
        List<Map<String, String>> listaColumnas = new ArrayList<>();

        File f = new File(rute);
        String[] listFiles = f.list();

        assert listFiles != null;
        String title = "";
        title += "KEY;";
        for (String files : listFiles) {
            if (files.contains(".txt")) {
                title += files + ";";
                Map<String, String> values = new HashMap<>();
                try {
                    List<String> lines = Files.readAllLines(Paths.get(rute + files), Charset.defaultCharset());
                    for (String line : lines) {
                        if (!line.isEmpty() && !line.equals("//")) {
                            //System.out.println(line);
                            String[] a = line.split("=>");
                            hashSetKeys.add(a[0]);
                           try {
                                values.put(a[0], a[1]);
                            } catch (Exception e) {
                               //e.printStackTrace();
                           }
                        }
                    }
                    listaColumnas.add(values);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (listaColumnas.isEmpty()) {
            throw new Exception("No se encontraron ficheros de entrada");
        }

        File fout = new File(rute + "out.csv");
        fout.createNewFile();
        try {
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(title);
            bw.newLine();
            for (String hashSetKey : hashSetKeys) {
                bw.write(hashSetKey + ";");
                for (Map<String, String> listaColumna : listaColumnas) {
                    String toWrite = listaColumna.get(hashSetKey);
                    if (toWrite != null && !toWrite.isEmpty()) {
                        bw.write(toWrite + ";");
                    }else{
                        bw.write(";");
                    }
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
