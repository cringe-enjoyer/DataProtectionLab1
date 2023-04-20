import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessBuilder process = new ProcessBuilder();
        ArrayList<String> com = new ArrayList<>();
        com.add("E:\\Git\\usr\\bin\\openssl.exe");
        com.add("dgst");
        com.add("-sha1");
        com.add("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing.txt");
        for (int i = 0; i < 20; i++) {
            trick(i);
            com.add("dgst");
            com.add("-sha1");
            com.add("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing" + i + ".txt");
        }
        process.command(com);
        /*process.command("E:\\Git\\usr\\bin\\openssl.exe", "dgst", "-sha1",
                "F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing.txt",
                "dgst", "-sha1", "F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing1.txt",
                "dgst", "-sha1", "F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing2.txt",
                "dgst", "-sha1", "F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing3.txt");*/
        process.redirectOutput(new File("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\out.txt"));
        process.start();
    }

    private static void trick(int i) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing" + (i == 0 ? "" : i) + ".txt"))) {
            String line = reader.readLine();
            FileWriter writer = new FileWriter("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing" + (i + 1) + ".txt");


            while(line != null) {
                sb.append(line).append("\n");
                line = line.replaceAll(" ", " ");
                writer.write(line + "\n");
                line = reader.readLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
/*        String text = sb.toString();
        text = text.replaceAll(" ", " ");
        if (i % 2 == 0)
            text = text.replaceAll("а", "а");
        try(FileWriter writer = new FileWriter("F:\\OpenSSLLabs\\Lab1\\Lab1\\res\\leasing" + (i + 1) + ".txt")) {
            writer.write(text);
        } catch (Exception e) {
            System.out.println(e);
        }*/
    }
}
