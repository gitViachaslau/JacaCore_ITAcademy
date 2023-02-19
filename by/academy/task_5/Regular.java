package by.academy.task_5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
    public static void main(String[] args) {
        String input = "https://jira.academy.com/secure/Dashboard.jspa?selectPageId=22702";
        // String input = "http://jira.academy.com/secure/Dashboard.jspa";
        Pattern pattern = Pattern.compile("(http://|https://)([a-z]{4})(\\.)([a-z]{7})(\\.)([a-z]{3})(\\/)([a-z]{6})(\\/)([A-Z]{1}[a-z]{8})(\\.)(([a-z]{4})|([a-z]{4}\\?[a-z]{6}[A-Z]{1}[a-z]{3}[A-Z]{1}[a-z]{1}\\=[0-9]{5}))");
        Matcher matcher = pattern.matcher(input);
        boolean match = matcher.matches();
        if (match) {
            System.out.println("Адабряю ");
        } else {
            System.out.println("Негодую");
        }
    }
}