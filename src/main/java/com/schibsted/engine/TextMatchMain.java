package com.schibsted.engine;

import com.schibsted.engine.app.AppTextMatch;

public class TextMatchMain {

    public static void main(String[] args) {
        AppTextMatch appTextMatch = new AppTextMatch(args);
        appTextMatch.execute();
    }

}
