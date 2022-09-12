package br.com.lwjgl.core;

import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
final class ShaderProgram extends Component implements Evaluable {
    private int program;

    private VertexShader vertexShader;
    private FragmentShader fragmentShader;

    @Override
    protected int memoryAddress() {
        return program;
    }

    @Override
    protected void run() {
        program = glCreateProgram();
        vertexShader = (VertexShader) Component.getInstance().get(VertexShader.class);
        fragmentShader = (FragmentShader) Component.getInstance().get(FragmentShader.class);

        if (program == NULL)
            throw new RuntimeException("Shader Program not initialized");

        glAttachShader(program, vertexShader.memoryAddress());
        glAttachShader(program, fragmentShader.memoryAddress());
        glLinkProgram(program);

        this.evaluate();
        glUseProgram(program);

        vertexShader.destroy();
        fragmentShader.destroy();
    }

    @Override
    public void evaluate () {
        IntBuffer success = MemoryStack.stackCallocInt(1);
        glGetProgramiv(memoryAddress(), GL_LINK_STATUS, success);

        if (success.get(0) == GL_FALSE) {
            final int infoLogLength = 512;
            log.error(glGetProgramInfoLog(program, infoLogLength));
        }
    }

    @Override
    protected void destroy() {
        glDeleteShader(program);
    }
}