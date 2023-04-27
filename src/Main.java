import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessBuilder process = new ProcessBuilder();
        ArrayList<String> com = new ArrayList<>();
        com.add("E:\\Git\\usr\\bin\\openssl.exe");
        com.add("dgst");
        com.add("-sha1");
        com.add("F:\\OpenSSLLabs\\Lab1\\res\\leasing.txt");
        for (int i = 0; i < 40; i++) {
            trick(i);
            com.add("dgst");
            com.add("-sha1");
            com.add("F:\\OpenSSLLabs\\Lab1\\res\\leasing" + i + ".txt");
        }
        process.command(com);
        process.redirectOutput(new File("F:\\OpenSSLLabs\\Lab1\\res\\out.txt"));
        process.start();
    }

    private static void trick(int i) {
        boolean check = false;
        int j = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("F:\\OpenSSLLabs\\Lab1\\res\\leasing" + (i == 0 ? "" : i) + ".txt"))) {
            String line = reader.readLine();
            FileWriter writer = new FileWriter("F:\\OpenSSLLabs\\Lab1\\res\\leasing" + (i + 1) + ".txt");
            while(line != null) {
                if (line.contains(" ") && i == j) {
                    line = line.replace(" ", "  ");
                    line = line.replace(" ", "\b");
                    check = true;
                }
                else if (!check && i < j && line.contains(" ")) {
                    line = line.replace(" ", "  ");
                    line = line.replace(" ", "\b");
                    check = true;
                }
                writer.write(line + "\n");
                line = reader.readLine();
                j++;
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
