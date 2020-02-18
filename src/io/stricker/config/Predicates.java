package io.stricker.config;

import org.rspeer.runetek.adapter.component.Item;

import java.util.function.Predicate;

public class Predicates {
    public static final Predicate<Item> JUG_OF_WINE = item -> item.getName().equals("Jug of wine");
    public static final Predicate<Item> KNIFE = item -> item.getName().equals("Knife");
    public static final Predicate<Item> BRUMA_ROOT = item -> item.getName().equals("Bruma root");
}
