package br.com.lwjgl.core;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

@Slf4j
public class Program {
    private static final Program instance = new Program();

    public static final Program getInstance() {
        return instance;
    }

    public void run () {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            log.error("Unable to initialize GLFW");
            throw new RuntimeException("Unable to initialize GLFW");
        }

        register();
        up();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void register () {
        log.info("Registering components");
        Component.add(new Window());
        Component.add(new VertexShader());
        Component.add(new FragmentShader());
        Component.add(new ShaderProgram());
        Component.add(new Game());
    }

    private void up () {
        log.info("Running components");
        Component.getInstance().forEach((key, value) -> value.run());
    }
}