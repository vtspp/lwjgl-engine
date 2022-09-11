package br.com.lwjgl.core;

import br.com.lwjgl.util.EngineFileRead;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

final class FragmentShader extends Component {
    private int fragmentShader;

    @Override
    protected int memoryAddress() {
        return fragmentShader;
    }

    @Override
    protected void run () {
        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        if (fragmentShader == NULL)
            throw new RuntimeException("Fragment Shader not initialized");

        glShaderSource(fragmentShader, EngineFileRead.read("fragment-shader.frag"));
        glCompileShader(fragmentShader);

        super.errorEvaluator(GL_COMPILE_STATUS);

        clear();
    }

    @Override
    protected void clear() {
        if (fragmentShader == NULL)
            return;
        glDeleteShader(fragmentShader);
    }
}