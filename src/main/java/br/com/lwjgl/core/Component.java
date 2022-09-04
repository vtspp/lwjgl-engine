package br.com.lwjgl.core;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Component {
    private static final Map<Class<?>, Component> components = new LinkedHashMap<>(0);

    public static Map<Class<?>, Component> getInstance() {
        return components;
    }

    protected static void add(Component component) {
        components.put(component.getClass(), component);
    }

    protected abstract void run ();

    protected abstract void clear ();
}