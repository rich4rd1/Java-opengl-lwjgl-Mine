package minecraft.render;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    // The window handle
    private long window;

    public long getWindowHandle() {
        return window;
    }

    public void init() {
        //Antes de inicializar a janela a verifica se o glfw inicializou, sem isso o programa não roda;
        if (!glfwInit()) {
            throw new IllegalStateException("Não foi possível inicializar GLFW");
        }

        // criamos a janela e especificamos largura, altura e titulo
        window = glfwCreateWindow(800, 600, "Minecraft", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Falha ao criar janela");
        }

        //Expecifica para o opengl qual janela estamos usando
        GLFW.glfwMakeContextCurrent(window);

        // permite o uso das funções do opengl
        org.lwjgl.opengl.GL.createCapabilities();
    }

    // Método de loop da janela
    public void loop() {
        //criação do loop: Enquanto a janela não for fechada faça:
        //Todos os frames irão passar por esse loop
        while (!glfwWindowShouldClose(window)) {
            //Llimpa a tela da janela a cada frame impedindo da imagem ficar sobreposta no proximo frame(loop)
            //O color_buffer limpa so as cores, entt no proximo frame nos apenas "pintamos" de novo
            GL11.glClear(GL_COLOR_BUFFER_BIT);

            //Aqui trocamos o buffer atual de exibição pelo que estava em espera
            //O openGL usa double buffering que é exatamente a ideia de enqualto um está sendo mostrado o outro está esperando sua vez.
            //E então ocorre o swap dos buffers
            glfwSwapBuffers(window);

            //Captura os eventos do sistema, teclado, mouse, cliques etc
            glfwPollEvents();
        }
    }

    public void cleanup() {
        //Destroi a janela
        GLFW.glfwDestroyWindow(window);

        //Finaliza as funções do opengl
        GLFW.glfwTerminate();
    }
}
