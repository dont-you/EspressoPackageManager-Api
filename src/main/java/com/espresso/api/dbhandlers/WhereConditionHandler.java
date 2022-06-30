package com.espresso.api.dbhandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhereConditionHandler{
    public String recursive(String str){
        Pattern pattern = Pattern.compile("\\(");
        Matcher matcher = pattern.matcher(str);
        String thisLevel = "";
        String result = "";
        if(matcher.find()){
            String nextLevelLeft = "";
            String nextLevelRight = "";
            String nextLevel = "";

            thisLevel = str.substring(0, matcher.end() - 1);

            if(thisLevel.equals("and") || thisLevel.equals("or")){
                nextLevel = str.substring(matcher.start() + 1, str.length() - 1);

                int index = 0;
                int i =0;
                int count_brekets = 0;
                for(char c: nextLevel.toCharArray()){

                    if(c==')')
                        count_brekets--;
                    if (c == '(')
                        count_brekets++;
                    else if (c == ')' && count_brekets==0){
                        index =i;
                        break;
                    }

                    i++;
                }

                nextLevelLeft = nextLevel.substring(0, index+1);
                nextLevelRight = nextLevel.substring(index + 2);

                result = "(" + recursive(nextLevelLeft) + " " + thisLevel.toUpperCase() + " " + recursive(nextLevelRight) + ")";

            }else{
                nextLevel = str.substring(matcher.start() + 1, str.length() - 1);
                String operator = "";
                if (thisLevel.equals("eq")){
                    operator = "=";
                }

                pattern=Pattern.compile("[,]");
                matcher = pattern.matcher(nextLevel);
                if(matcher.find()){
                    nextLevelLeft = nextLevel.substring(0,matcher.start());
                    nextLevelRight = nextLevel.substring(matcher.start()+1);
                }
                result = nextLevelLeft + operator + "'" + nextLevelRight + "'";
            }
        }
        return result;
    }
}
