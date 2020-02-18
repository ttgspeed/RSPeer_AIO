package io.stricker.config;

import org.rspeer.runetek.adapter.component.Item;

import java.util.function.Predicate;

public class Predicates {
    public static final Predicate<Item> WINE_PREDICATE = item -> item.getName().equals("Jug of wine");
    public static final Predicate<Item> KNIFE_PREDICATE = item -> item.getName().equals("Knife");
}
