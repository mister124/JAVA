package Assign001;
import java.nio.charset.Charset;
import java.io.OutputStreamWriter;

public class DefaultCharset {
    public static void main(String[] args) {
        System.out.println("Default Charset of JVM: " + Charset.defaultCharset());
        System.out.println("System.out Charset (file.encoding): " + System.getProperty("file.encoding"));
        System.out.println("Console Charset: " + new OutputStreamWriter(System.out).getEncoding());
    }
}