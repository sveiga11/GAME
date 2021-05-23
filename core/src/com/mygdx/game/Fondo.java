package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {


    Animacion animacion = new Animacion(3,
            new Texture("fondo1/frame_00_delay-0.03s.gif"),
            new Texture("fondo1/frame_01_delay-0.03s.gif"),
            new Texture("fondo1/frame_02_delay-0.03s.gif"),
            new Texture("fondo1/frame_03_delay-0.03s.gif"),
            new Texture("fondo1/frame_04_delay-0.03s.gif"),
            new Texture("fondo1/frame_05_delay-0.03s.gif"),
            new Texture("fondo1/frame_06_delay-0.03s.gif"),
            new Texture("fondo1/frame_07_delay-0.03s.gif"),
            new Texture("fondo1/frame_08_delay-0.03s.gif"),
            new Texture("fondo1/frame_09_delay-0.03s.gif"),
            new Texture("fondo1/frame_10_delay-0.03s.gif"),
            new Texture("fondo1/frame_11_delay-0.03s.gif"),
            new Texture("fondo1/frame_12_delay-0.03s.gif"),
            new Texture("fondo1/frame_13_delay-0.03s.gif"),
            new Texture("fondo1/frame_14_delay-0.03s.gif"),
            new Texture("fondo1/frame_15_delay-0.03s.gif"),
            new Texture("fondo1/frame_16_delay-0.03s.gif"),
            new Texture("fondo1/frame_17_delay-0.03s.gif"),
            new Texture("fondo1/frame_18_delay-0.03s.gif"),
            new Texture("fondo1/frame_19_delay-0.03s.gif"),
            new Texture("fondo1/frame_20_delay-0.03s.gif"),
            new Texture("fondo1/frame_21_delay-0.03s.gif"),
            new Texture("fondo1/frame_22_delay-0.03s.gif"),
            new Texture("fondo1/frame_23_delay-0.03s.gif"),
            new Texture("fondo1/frame_24_delay-0.03s.gif"),
            new Texture("fondo1/frame_25_delay-0.03s.gif"),
            new Texture("fondo1/frame_26_delay-0.03s.gif"),
            new Texture("fondo1/frame_27_delay-0.03s.gif"),
            new Texture("fondo1/frame_28_delay-0.03s.gif"),
            new Texture("fondo1/frame_29_delay-0.03s.gif")
    );

    public void render(SpriteBatch batch) {
        batch.draw(animacion.obtenerFrame(), 0, 0, 640, 480);
    }
}