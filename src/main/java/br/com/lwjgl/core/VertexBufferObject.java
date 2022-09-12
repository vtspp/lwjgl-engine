package br.com.lwjgl.core;

import static org.lwjgl.opengl.GL46.*;

public class VertexBufferObject extends Component {
    private int vertexBufferObject;
    private int $null;

    @Override
    protected int memoryAddress() {
        return vertexBufferObject;
    }

    @Override
    protected void run() {
        vertexBufferObject = glGenBuffers();
    }

    @Override
    protected void destroy() {
        glDeleteBuffers(vertexBufferObject);
    }

    public void bind () {
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
    }

    public void unbind () {
        glBindBuffer(GL_ARRAY_BUFFER, $null);
    }
}