package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigo {
    Animacion animacion = new Animacion(15,
            new Texture("alien1.png"),
            new Texture("alien2.png"),
            new Texture("alien3.png"),
            new Texture("alien4.png")
    );
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(60);


    Enemigo() {
        x = Utils.random.nextInt(640);
        y = 480;
        w = 64;
        h = 48;
        vx = -2;
        vy = 0;
    }

    public void update() {
        y += vy;
        x += vx;

        if (cambioVelocidad.suena()) {
            vx = Utils.random.nextInt(6) - 3;
            vy = -(Utils.random.nextInt(3)+1);
        }

    }

    void render(SpriteBatch batch) {
        batch.draw(animacion.obtenerFrame(), x, y, w, h);}
}