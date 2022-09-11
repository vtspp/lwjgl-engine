package br.com.lwjgl.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
final class Window extends Component {
    private long window;
    private int width = 800;
    private int height = 400;
    private String title = "Game Engine";

    @Override
    protected int memoryAddress() {
        return (int) window;
    }

    @Override
    protected void run () {
        log.info("Running {}", Window.class.getSimpleName());
        window = glfwCreateWindow(width, height, title, NULL, NULL );

        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        setup();

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        });

        GL.createCapabilities();
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
    }

    private void setup () {
        GLFWVidMode monitor = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (monitor.width() - width) / 2, (monitor.height() - height) / 2);

        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(GLFW_TRUE);
        glfwShowWindow(window);
    }

    protected boolean isClosed () {
        return glfwWindowShouldClose(window);
    }

    @Override
    protected void clear () {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    protected void destroy () {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }
}