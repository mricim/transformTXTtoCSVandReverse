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

        Arrays.sort(listFiles);

        Arrays.sort(listFiles, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // the +1 is to avoid including the '.' in the extension and to avoid exceptions
                // EDIT:
                // We first need to make sure that either both files or neither file
                // has an extension (otherwise we'll end up comparing the extension of one
                // to the start of the other, or else throwing an exception)
                final int s1Dot = s1.lastIndexOf('.');
                final int s2Dot = s2.lastIndexOf('.');
                if ((s1Dot == -1) == (s2Dot == -1)) { // both or neither
                    s1 = s1.substring(s1Dot + 1);
                    s2 = s2.substring(s2Dot + 1);
                    return s2.compareTo(s1);
                } else if (s1Dot == -1) { // only s2 has an extension, so s1 goes first
                    return -1;
                } else { // only s1 has an extension, so s1 goes second
                    return 1;
                }
            }
        });

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
            }else if(files.contains(".php")){
                title += files + ";";
                Map<String, String> values = new HashMap<>();
                try {
                    List<String> lines = Files.readAllLines(Paths.get(rute + files), Charset.defaultCharset());
                    for (String line : lines) {
                        if (!line.isEmpty() && !line.equals("//")&&!line.equals("<?php")&&!line.equals("?>")) {
                            //System.out.println(line);
                            line=line.replace("$messages['","").replace("';","");
                            String[] a = line.split("']='");
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

        File fileOut = new File(rute + "out.csv");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
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
