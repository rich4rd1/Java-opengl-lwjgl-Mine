package minecraft;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Render {

    // The window handle
    private long window;

    // variaveis de localidade e velocidade
    private float x = 0.0f;
    private float y = 0.0f;
    private float speed = 1.5f;

    // variáveis de movimentação 
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    //variáveis do deltaTime
    private double lastTime;
    private double deltaTime;

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

        // Chama o metodo de inputs de teclado
        setupInput();

        // Inicia a captura de tempo para a variável lastTime 
        lastTime = GLFW.glfwGetTime();
    }

    private void setupInput() {
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

    private void update() {
        // Captura e calculo do tempo atual 
        double courrentTime = GLFW.glfwGetTime();
        deltaTime = (courrentTime - lastTime);
        lastTime = courrentTime;

        //As variáveis dx e dy são adicionadas para retirar o "buff" de velocidade que se tinha ao pressionar duas teclas juntas 
        float dx = 0f;
        float dy = 0f;

        if (leftPressed) {
            dx -= 1f;
        }
        if (rightPressed) {
            dx += 1f;
        }
        if (upPressed) {
            dy += 1f;
        }
        if (downPressed) {
            dy -= 1f;
        }

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        x += dx * speed * deltaTime;
        y += dy * speed * deltaTime;

    }

    //Método que desenha um quadrado na tela e permite-nos movê-lo 
    private void renderSquare() {
        glColor3f(1.0f, 1.0f, 1.0f);

        glBegin(GL11.GL_QUADS);
        glVertex2f(x - 0.1f, y - 0.1f);
        glVertex2f(x + 0.1f, y - 0.1f);
        glVertex2f(x + 0.1f, y + 0.1f);
        glVertex2f(x - 0.1f, y + 0.1f);
        glEnd();
    }

    public void run() {
        init();
        loop();
        cleanup();

    }

    // Método de loop da janela
    private void loop() {

        //criação do loop: Enquanto a janela não for fechada faça:
        //Todos os frames irão passar por esse loop
        while (!glfwWindowShouldClose(window)) {
            //Llimpa a tela da janela a cada frame impedindo da imagem ficar sobreposta no proximo frame(loop)
            //O color_buffer limpa so as cores, entt no proximo frame nos apenas "pintamos" de novo
            GL11.glClear(GL_COLOR_BUFFER_BIT);
            //update das variáveis de movimentação
            update();
            //Desenha o quadrado na tela
            render();

            //Aqui trocamos o buffer atual de exibição pelo que estava em espera
            //O openGL usa double buffering que é exatamente a ideia de enqualto um está sendo mostrado o outro está esperando sua vez.
            //E então ocorre o swap dos buffers
            glfwSwapBuffers(window);

            //Captura os eventos do sistema, teclado, mouse, cliques etc
            glfwPollEvents();
        }
    }

    // Chamada do quadrado
    private void render() {
        renderSquare();
        //renderUI()
        //renderWorld()
    }

    private void cleanup() {
        //Destroi a janela
        GLFW.glfwDestroyWindow(window);

        //Finaliza as funções do opengl
        GLFW.glfwTerminate();
    }

}
