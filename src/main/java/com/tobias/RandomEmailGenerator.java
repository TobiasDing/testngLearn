package com.tobias;

public class RandomEmailGenerator {

    public String generate(String userName) {
        return String.format("%s@tobias.com", userName);
    }
}
