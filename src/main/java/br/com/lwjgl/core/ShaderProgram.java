package br.com.lwjgl.core;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

final class ShaderProgram extends Component {
    private int program;

    @Override
    protected int memoryAddress() {
        return program;
    }

    @Override
    protected void run() {
        program = glCreateProgram();

        if (program == NULL)
            throw new RuntimeException("Shader Program not initialized");

        glAttachShader(program, Component.getInstance().get(VertexShader.class).memoryAddress());
        glAttachShader(program, Component.getInstance().get(FragmentShader.class).memoryAddress());
        glLinkProgram(program);

        super.errorEvaluator(GL_LINK_STATUS);

    }

    @Override
    protected void clear() {
    }
}