package br.com.lwjgl.core;

import static org.lwjgl.opengl.GL46.*;

final class VertexBufferObject extends Component {
    private int vertexBufferObject;
    private int $null;

    @Override
    protected int memoryAddress() {
        return vertexBufferObject;
    }

    @Override
    protected void run() {
        vertexBufferObject = glGenBuffers();

        if (vertexBufferObject == $null)
            throw new RuntimeException("Vertex Buffer Object not initialize");
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