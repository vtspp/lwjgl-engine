package br.com.lwjgl.core;

import br.com.lwjgl.util.EngineFileRead;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.NULL;

@Slf4j
final class FragmentShader extends Component implements Evaluable {
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

        this.evaluate();
    }

    @Override
    public void evaluate() {
        IntBuffer success = MemoryStack.stackCallocInt(1);
        glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, success);

        if (success.get(0) == GL_FALSE) {
            log.error(glGetShaderInfoLog(fragmentShader, 512));
        }
    }

    @Override
    protected void destroy() {
        if (fragmentShader == NULL)
            return;
        glDeleteShader(fragmentShader);
    }
}