package minecraft.model;
import minecraft.input.KeyboardInput;

public class Square extends MovableEntities{ 
    private float width;
    private float height;
    private KeyboardInput input;


    public Square(float x, float y, float width, float height, KeyboardInput input){
        super(x, y, 1.5f, input);
        this.width = width;
        this.height = height;
        this.input = input;
    }

    @Override
    public void update(float deltaTime) {
        if(input == null) return;
        
        float dx = 0, dy = 0;

        if (input.isLeftPressed()) dx -= 1;
        if (input.isRightPressed()) dx += 1;
        if (input.isUpPressed()) dy += 1;
        if (input.isDownPressed()) dy -= 1;
    
        float length = (float) Math.sqrt(dx*dx + dy*dy);
            if (length != 0) {
            dx /= length;
            dy /= length;
        }
        move(dx, dy, deltaTime); // move() é herdado da superclasse moveableEntities
    }



    @Override
    public String toString(){
        return "Square:\n x: " + x + "\ny: "+ y + "\nwidth: " + width + "\nheigth: " + height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
