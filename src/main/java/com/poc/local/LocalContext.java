package com.poc.local;

public class LocalContext {

    private static ThreadLocal<String> context = new ThreadLocal<>();

    public static String get() {
        return context.get();
    }

    public static void store(long id) {
        if(context.get() == null) {
            context.set("context " + id);
        }
    }

    public static void clean() {
        context.remove();
    }
}
