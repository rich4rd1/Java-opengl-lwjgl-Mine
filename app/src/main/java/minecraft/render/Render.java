
package minecraft.render;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import minecraft.model.Block;
import minecraft.model.Square;

public class Render {

    public void renderSquare(Square square) {
        glColor3f(1.0f, 1.0f, 1.0f);

        glBegin(GL_QUADS);
        glVertex2f(square.getX() - 0.1f, square.getY() - 0.1f);
        glVertex2f(square.getX() + 0.1f, square.getY() - 0.1f);
        glVertex2f(square.getX() + 0.1f, square.getY() + 0.1f);
        glVertex2f(square.getX() - 0.1f, square.getY() + 0.1f);
        glEnd();
    }

    public void render(Square square) {
        renderSquare(square);
    }


    public void renderBlock(Block block){
            // cor do bloco: marrom para a terra
    glColor3f(0.6f, 0.4f, 0.2f); 
    glBegin(GL_QUADS);
    glVertex2f(block.getX() - block.getWidth()/2, block.getY() - block.getHeight()/2);
    glVertex2f(block.getX() + block.getWidth()/2, block.getY() - block.getHeight()/2);
    glVertex2f(block.getX() + block.getWidth()/2, block.getY() + block.getHeight()/2);
    glVertex2f(block.getX() - block.getWidth()/2, block.getY() + block.getHeight()/2);
    glEnd();

        // camada de grama em cima
    glColor3f(0.0f, 0.8f, 0.0f); 
    glBegin(GL_QUADS);
    float grassHeight = block.getHeight() * 0.2f; // 20% da altura
    glVertex2f(block.getX() - block.getWidth()/2, block.getY() + block.getHeight()/2 - grassHeight);
    glVertex2f(block.getX() + block.getWidth()/2, block.getY() + block.getHeight()/2 - grassHeight);
    glVertex2f(block.getX() + block.getWidth()/2, block.getY() + block.getHeight()/2);
    glVertex2f(block.getX() - block.getWidth()/2, block.getY() + block.getHeight()/2);
    glEnd();
    }
}
