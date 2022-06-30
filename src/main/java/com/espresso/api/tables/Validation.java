package com.espresso.api.tables;

public class Validation{
    public static boolean containsAnyCharacters(String line,String wantedCharacters){
        BooleanWrapper exitFlag = new BooleanWrapper(false);
        BooleanWrapper result  = new BooleanWrapper(false);

        line.chars().
            takeWhile(e -> exitFlag.value!=true).
            forEach(checked_character -> {
                    wantedCharacters.chars().
                        takeWhile(e -> exitFlag.value!=true).
                        forEach(wanted_character -> {
                                if(checked_character==wanted_character){
                                    exitFlag.value = true;
                                    result.value = true;
                                }
                    });
            });

        return result.value;
    }

    static class BooleanWrapper{
        boolean value;

        public BooleanWrapper(boolean value) {
            this.value = value;
        }
    }
}
