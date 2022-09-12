package br.com.lwjgl.core;

import br.com.lwjgl.util.EngineFileRead;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
final class VertexShader extends Component implements Evaluable {
    private int vertexShader;

    @Override
    protected int memoryAddress() {
        return vertexShader;
    }

    @Override
    protected void run () {
        vertexShader = glCreateShader(GL_VERTEX_SHADER);

        if (vertexShader == NULL)
            throw new RuntimeException("Vertex Shader not initialized");

        glShaderSource(vertexShader, EngineFileRead.read("vertex-shader.vert"));
        glCompileShader(vertexShader);

        this.evaluate();
    }

    @Override
    public void evaluate() {
        IntBuffer success = MemoryStack.stackCallocInt(1);
        glGetShaderiv(memoryAddress(), GL_COMPILE_STATUS, success);

        if (success.get(0) == GL_FALSE) {
            log.error(glGetShaderInfoLog(vertexShader, 512));
        }
    }

    @Override
    protected void destroy() {
        if (vertexShader == NULL)
            return;
        glDeleteShader(vertexShader);
    }
}