package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Fondo fondo;
	Jugador jugador;
	Emoji emoji;
	List<Enemigo> enemigos = new ArrayList<>();
	List<Disparo> disparosAEliminar = new ArrayList<>();
	List<Enemigo> enemigosAEliminar = new ArrayList<>();
	Temporizador temporizadorNuevoEnemigo = new Temporizador(120);
	ScoreBoard scoreboard;
	boolean gameover;
	Music[] music = new Music[3];
	float volumen = 0.5f;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		emoji = new Emoji();
		font.setColor(Color.WHITE);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(2f);

		inicializarJuego();
	}

	void inicializarJuego(){
		fondo = new Fondo();
		jugador = new Jugador();
		enemigos = new ArrayList<>();
		temporizadorNuevoEnemigo = new Temporizador(120);
		disparosAEliminar = new ArrayList<>();
		enemigosAEliminar = new ArrayList<>();
		scoreboard = new ScoreBoard();
		music[0] = Gdx.audio.newMusic(Gdx.files.getFileHandle("start_game.mp3", Files.FileType.Internal));
		music[0].setVolume(volumen);
		music[0].play();
		gameover = false;
	}

	void update() {
		Temporizador.framesJuego += 1;

		if (temporizadorNuevoEnemigo.suena()) enemigos.add(new Enemigo());

		if(!gameover) {
			jugador.update();
		}

		for (Enemigo enemigo : enemigos) enemigo.update();              // enemigos.forEach(Enemigo::update);


		for (Enemigo enemigo : enemigos) {
			for (Disparo disparo : jugador.disparos) {
				if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo.x, enemigo.y, enemigo.w, enemigo.h)) {
					disparosAEliminar.add(disparo);
					enemigosAEliminar.add(enemigo);
					jugador.puntos++;

					music[1] = Gdx.audio.newMusic(Gdx.files.getFileHandle("enemigo_muerto.mp3", Files.FileType.Internal));
					music[1].setVolume(volumen);
					music[1].play();

					break;
				}
			}

			if (!gameover && !jugador.muerto && Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
				jugador.morir();
				if (jugador.vidas == 0){
					gameover = true;
				}
			}

			if (jugador.puntos >= 10){
				temporizadorNuevoEnemigo.frecuencia = 57;
				enemigo.animacion.duracion = 12;
				music[2] = Gdx.audio.newMusic(Gdx.files.getFileHandle("10.mp3", Files.FileType.Internal));
				music[2].setVolume(volumen);
				music[2].play();
			}
			if (jugador.puntos >= 20){
				temporizadorNuevoEnemigo.frecuencia = 47;
				enemigo.animacion.duracion = 9;
			}
			if (jugador.puntos >= 30){
				temporizadorNuevoEnemigo.frecuencia = 37;
				enemigo.animacion.duracion = 7;
			}
			if (jugador.puntos >= 40){
				temporizadorNuevoEnemigo.frecuencia = 27;
				enemigo.animacion.duracion = 4;
			}
			if (jugador.puntos >= 50){
				temporizadorNuevoEnemigo.frecuencia = 17;
				enemigo.animacion.duracion = 1;
			}

			if (enemigo.x < -enemigo.w) enemigosAEliminar.add(enemigo);
		}

		for (Disparo disparo : jugador.disparos)
			if (disparo.x > 640)
				disparosAEliminar.add(disparo);

		for (Disparo disparo : disparosAEliminar) jugador.disparos.remove(disparo);       // disparosAEliminar.forEach(disparo -> jugador.disparos.remove(disparo));
		for (Enemigo enemigo : enemigosAEliminar) enemigos.remove(enemigo);               // enemigosAEliminar.forEach(enemigo -> enemigos.remove(enemigo));
		disparosAEliminar.clear();
		enemigosAEliminar.clear();

		if(gameover) {
			int result = scoreboard.update(jugador.puntos);
			if(result == 1) {
				inicializarJuego();
			} else if (result == 2) {
				Gdx.app.exit();
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		batch.begin();
		fondo.render(batch);
		jugador.render(batch);
		emoji.render(batch);
		for (Enemigo enemigo : enemigos) enemigo.render(batch);
		font.draw(batch, "" + jugador.vidas, 540, 440);
		font.draw(batch, "" + jugador.puntos, 540, 380);

		if (gameover){
			scoreboard.render(batch, font);
		}
		batch.end();
	}
}
