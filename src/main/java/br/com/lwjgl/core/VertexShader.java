package br.com.lwjgl.core;

import br.com.lwjgl.util.EngineFileRead;
import lombok.Getter;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Getter
final class VertexShader extends Component {
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

        super.errorEvaluator(GL_COMPILE_STATUS);

        clear();
    }

    @Override
    protected void clear() {
        if (vertexShader == NULL)
            return;
        glDeleteShader(vertexShader);
    }
}