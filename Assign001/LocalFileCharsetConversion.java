package Assign001;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LocalFileCharsetConversion {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "cp949.txt";
        String outputFilePath = "local_output_utf8.txt";

        Charset inputCharset = StandardCharsets.UTF_8;
        Charset outputCharset = StandardCharsets.UTF_8;

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(inputFilePath),
                    inputCharset
                )
            );

            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(outputFilePath),
                    outputCharset
                )
            )
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }

        System.out.println(
            "파일 변환 완료: "
            + inputFilePath + " (" + inputCharset + ") -> "
            + outputFilePath + " (" + outputCharset + ")"
        );
    }
}