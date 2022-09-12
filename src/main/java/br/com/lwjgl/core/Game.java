package br.com.lwjgl.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
final class Game extends Component {
    private Window window;
    private ShaderProgram program;
    private VertexArrayObject vertexArrayObject;
    private VertexBufferObject vertexBufferObject;

    @Override
    protected int memoryAddress() {
        return 0;
    }

    @Override
    protected void run () {
        log.info("Running {}", Game.class.getSimpleName());
        window = (Window) Component.getInstance().get(Window.class);
        program = (ShaderProgram) Component.getInstance().get(ShaderProgram.class);
        vertexArrayObject = (VertexArrayObject) Component.getInstance().get(VertexArrayObject.class);
        vertexBufferObject = (VertexBufferObject) Component.getInstance().get(VertexBufferObject.class);

        processVertexArrayObject();
        processVertexBufferObject();

        while (!window.isClosed()) {
            window.clear();

            glDrawArrays(GL_TRIANGLES, 0, 3);

            window.swapBuffers();
            glfwPollEvents();
        }

        destroy();
    }

    private void processVertexArrayObject () {
        vertexArrayObject.bind();
        vertexArrayObject.enableIndexVertexArray(0);
    }

    private void processVertexBufferObject() {
        float[] vertices = {
                -  0.5f, - 0.5f, 0.0f,
                   0.5f, - 0.5f, 0.0f,
                   0.0f, 0.5f, 0.0f
        };
        vertexBufferObject.bind();
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);

        vertexBufferObject.unbind();
    }

    @Override
    protected void destroy() {
        window.destroy();
        program.destroy();
        vertexArrayObject.destroy();
        vertexBufferObject.destroy();
    }
}