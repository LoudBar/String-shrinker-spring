package ru.loudbar.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.loudbar.dao.StringRepo;

@Service
@RequiredArgsConstructor
public class StringService {

    private final StringRepo stringRepo;

    public String encode(String string) {

        int id = stringRepo.putString(string);

        char[] base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder shortString = new StringBuilder();

        while (id > 0) {
            shortString.append(base62[id % 62]);
            id = id / 62;
        }
        return shortString.reverse().toString();
    }

    public String decode(String shortString) {

        int id = 0;

        for (int i = 0; i < shortString.length(); i++) {
            if ('a' <= shortString.charAt(i) && shortString.charAt(i) <= 'z')
                id = id * 62 + shortString.charAt(i) - 'a';
            if ('A' <= shortString.charAt(i) && shortString.charAt(i) <= 'Z')
                id = id * 62 + shortString.charAt(i) - 'A' + 26;
            if ('0' <= shortString.charAt(i) && shortString.charAt(i) <= '9')
                id = id * 62 + shortString.charAt(i) - '0' + 52;
        }

        if (id > stringRepo.getDbSize())
            return null;

        return stringRepo.retrieveString(id);
    }
}
