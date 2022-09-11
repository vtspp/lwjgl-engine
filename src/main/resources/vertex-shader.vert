#version 330 core

layout (location = 0) in vec3 vector;

void main() {
    gl_position = vec4(vector.x, vector.y, vector.z, 1.0);
}