package minecraft.core;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

import minecraft.input.KeyboardInput;
import minecraft.model.Block;
import minecraft.model.MovableEntities;
import minecraft.model.Square;
import minecraft.render.Render;
import minecraft.render.Window;

public class Game {

    //variáveis
    private Window window;
    private KeyboardInput input;
    private Render render;
    private Square quadrado;
    private List<MovableEntities> movableEntities;
    private List<Block> blocks;

    private void init() {
        //instanciando e iniciando uma janela
        window = new Window();
        window.init();

        //instanciar o input 
        input = new KeyboardInput();
        //garante que qualquer tecla pressionada seja registrada no keyboardInput
        input.setupInput(window.getWindowHandle());

        //instancia das entidades que se movem
        movableEntities = new ArrayList<>();

        //instancia do bloco
        blocks = new ArrayList<>();
        blocks.add(new Block(0f, -0.5f, 0.5f, 0.5f));

        //instancia do quadrado 
        quadrado = new Square(0f, 0f, 0.2f, 0.2f, input);
        movableEntities.add(quadrado);

        //intancia do render 
        render = new Render();


    }

    public void run() {
        init();
        double lastTime = org.lwjgl.glfw.GLFW.glfwGetTime(); // inicializa o lastTime

        // O handle retorna a referencia da janela
        while (!org.lwjgl.glfw.GLFW.glfwWindowShouldClose(window.getWindowHandle())) {
            //limpa a tela a cada frame 
            org.lwjgl.opengl.GL11.glClear(org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT);
            
            //variavel que guarda o tempo atual
            double currentTime = glfwGetTime();
            //calculo do deltaTime
            double deltaTime = currentTime - lastTime;
            //atualiza o deltaTime com o tempo final atual
            lastTime = currentTime;

            //aplica o deltaTime para os objetos moveis
            for (MovableEntities entity : movableEntities) {
                entity.update((float) deltaTime);
            }

            for(Block block : blocks){
                render.renderBlock(block);
            }

            //desenho de quadrados
            for (MovableEntities entity : movableEntities) {
                if (entity instanceof Square) {
                    render.renderSquare((Square) entity);
                }
            }
            //troca de buffers
            org.lwjgl.glfw.GLFW.glfwSwapBuffers(window.getWindowHandle());

            //processamento de eventos do sistema 
            org.lwjgl.glfw.GLFW.glfwPollEvents();
        }

    }

}
