package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.json.JSONObject;
import org.json.JSONTokener;

public class NuAuthorizer {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, Charset.defaultCharset()))) {
            var tokener = new JSONTokener(reader);
            var json = new JSONObject(tokener);
            System.out.println("All lines: ->" + json);
        }


    }
}
