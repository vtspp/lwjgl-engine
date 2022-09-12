package br.com.lwjgl.core;

import lombok.extern.slf4j.Slf4j;

import static org.lwjgl.opengl.GL46.*;

@Slf4j
public class VertexArrayObject extends Component {
    private int vertexArrayObject;
    private int $null;

    @Override
    protected int memoryAddress() {
        return vertexArrayObject;
    }

    @Override
    protected void run() {
        vertexArrayObject = glCreateVertexArrays();
    }

    @Override
    protected void destroy() {
        glDeleteVertexArrays(vertexArrayObject);
    }

    public void bind () {
        glBindVertexArray(vertexArrayObject);
    }

    public void unbind () {
        glBindVertexArray($null);
    }

    public void enableIndexVertexArray (int index) {
        glEnableVertexAttribArray(index);
    }
}