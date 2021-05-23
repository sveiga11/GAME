package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Emoji {
    Texture texture = new Texture("vidas.png");
    Texture texture2 = new Texture("sniper.png");

    public void render(SpriteBatch batch) {
        batch.draw(texture, 575, 410, 45, 35);
        batch.draw(texture2, 575, 350, 45, 35);

    }

}
