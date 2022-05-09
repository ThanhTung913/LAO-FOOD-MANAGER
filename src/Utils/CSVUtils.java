package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <E> void writeFile(String pathFile, List<E> listProduct) throws IOException {
        File file = new File(pathFile);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathFile));
        for (E item : listProduct) {
            bufferedWriter.write(item.toString() + "\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static List<String> readFile(String pathFile) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            String line;
            while ((line = bufferedReader.readLine()) != null && line.trim().length() > 0) {
                lines.add(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
