package minecraft.model;

import minecraft.input.KeyboardInput;

public abstract class MovableEntities {
    protected float x;
    protected float y;
    protected float speed;
    protected KeyboardInput input;

    public MovableEntities(float x, float y, float speed, KeyboardInput input){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.input = input;
    }

    //Metodo generico de update de movimentação 
    public void move(float dx, float dy, float deltaTime){
        x += dx * speed * deltaTime;
        y += dy * speed * deltaTime;
    }

    //Vai permitir que cada objeto defina seu deltaTime de movimento por essa classe de movimento.
    public abstract void update(float deltaTime);
}
