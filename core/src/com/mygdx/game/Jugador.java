package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    Animacion animacion = new Animacion(10,
            new Texture("nave2.png"),
            new Texture("nave3.png"),
            new Texture("nave4.png")
    );

    Animacion animacion_gameover = new Animacion(10,
            new Texture("finjuego.png")
    );

    Animacion animacion_jugadorherido = new Animacion(10,
            new Texture("nave2muerta.png"),
            new Texture("nave3muerta.png"),
            new Texture("nave4muerta.png")
    );

    float x, y, w, h, v;
    List<Disparo> disparos = new ArrayList<>();
    int vidas = 3;
    int puntos = 0;
    boolean muerto = false;
    Temporizador temporizadorFireRate = new Temporizador(20);
    Temporizador temporizadorRespawn = new Temporizador(120, false);
    Music[] music = new Music[2];
    float volumen = 0.2f;

    Jugador() {
        x = 100;
        y = 100;
        w = 43 * 3;
        h = 21 * 3;
        v = 10;
    }

    void update() {
        for (Disparo disparo : disparos) disparo.update();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= v;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && temporizadorFireRate.suena() && !muerto) {
            disparos.add(new Disparo(x + w / 2, y + h));
        }

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 640 - w) x = 640 - w;
        if (y > 480 - h) y = 480 - h;

        if (temporizadorRespawn.suena()) {
            muerto = false;
        }
    }

    void render(SpriteBatch batch) {

        if (vidas == 0){
            batch.draw(animacion_gameover.obtenerFrame(), x, y, w, h);
            music[0] = Gdx.audio.newMusic(Gdx.files.getFileHandle("game_over.wav", Files.FileType.Internal));
            music[0].setVolume(volumen);
            music[0].play();
        } else if (muerto){
            batch.draw(animacion_jugadorherido.obtenerFrame(), x, y, w, h);
        } else {
            batch.draw(animacion.obtenerFrame(), x, y, w, h);
        }

        for (Disparo disparo : disparos) disparo.render(batch);
    }

    public void morir() {
        vidas--;
        muerto = true;
        temporizadorRespawn.activar();
    }
}