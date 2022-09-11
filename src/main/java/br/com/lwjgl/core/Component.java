package br.com.lwjgl.core;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glGetProgramiv;

@Slf4j
public abstract class Component {
    private static final Map<Class<?>, Component> components = new LinkedHashMap<>(0);

    public static Map<Class<?>, Component> getInstance() {
        return components;
    }

    protected static void add(Component component) {
        components.put(component.getClass(), component);
    }

    protected abstract int memoryAddress ();

    protected void errorEvaluator (int type) {
        IntBuffer error = MemoryStack.stackCallocInt(1);
        glGetProgramiv(memoryAddress(), type, error);

        if (error.get() == GL_TRUE) {
            printError();
        }
    }

    protected void printError () {
        log.error(GL46.glGetProgramInfoLog(memoryAddress(), 1024));
    }

    protected abstract void run ();

    protected abstract void clear ();
}