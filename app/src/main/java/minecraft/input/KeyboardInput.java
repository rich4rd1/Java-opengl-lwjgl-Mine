package minecraft.input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class KeyboardInput {

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }

    public void setupInput(long window) {
        glfwSetKeyCallback(window, new GLFWKeyCallbackI() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {

                boolean pressed = action == GLFW_PRESS;

                if (key == GLFW_KEY_A) {
                    leftPressed = pressed;
                }
                if (key == GLFW_KEY_D) {
                    rightPressed = pressed;
                }
                if (key == GLFW_KEY_W) {
                    upPressed = pressed;
                }
                if (key == GLFW_KEY_S) {
                    downPressed = pressed;
                }

                // ESC fecha a janela
                if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });
    }
}
