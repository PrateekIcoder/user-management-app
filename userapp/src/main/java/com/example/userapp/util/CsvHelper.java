package com.example.userapp.util;

import com.example.userapp.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {

    public static List<User> csvToUsers(MultipartFile file) {
        try {
            List<User> users = new ArrayList<>();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(file.getInputStream())
            );

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // skip header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                User user = new User();
                user.setUsername(data[0].trim());
                user.setPassword(data[1].trim());

                users.add(user);
            }

            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file");
        }
    }
}
