#version 460

layout (location = 0) in vec3 vector;

void main() {
    gl_Position = vec4(vector, 1.0);
}