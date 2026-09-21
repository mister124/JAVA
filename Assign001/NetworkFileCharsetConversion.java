package Assign001;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NetworkFileCharsetConversion {
    public static void main(String[] args) throws IOException {
        String urlString =
            "https://raw.githubusercontent.com/python/cpython/refs/heads/main/Lib/test/cjkencodings/cp949.txt";

        String outputFilePath = "network_output_utf8.txt";

        Charset inputCharset = Charset.forName("MS949");
        Charset outputCharset = StandardCharsets.UTF_8;

        URL url = new URL(urlString);

        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), inputCharset)
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

        System.out.println("네트워크 파일 다운로드 및 변환 완료!");
    }
}