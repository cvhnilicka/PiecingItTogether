package com.cormickhnilicka.piecingittogether;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormickhnilicka.piecingittogether.MarioBros.MarioBros;
import com.cormickhnilicka.piecingittogether.MarioBros.Screens.PlayScreen;
import com.cormickhnilicka.piecingittogether.Navigation.Views.EndScreen;
import com.cormickhnilicka.piecingittogether.Navigation.Views.LoadingScreen;
import com.cormickhnilicka.piecingittogether.Navigation.Views.MainScreen;
import com.cormickhnilicka.piecingittogether.Navigation.Views.MenuScreen;

public class PiecingItTogether extends Game {
	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private PlayScreen playScreen;
	private MarioBros marioBros;


	public final static int MENU = 0;
	public final static int APPLICATION = 2;
	public final static int ENDGAME = 3;
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	public void changeScreen(int screen) {
		switch (screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case APPLICATION:
				if (mainScreen == null) mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case ENDGAME:
				if (endScreen == null) endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;
		}
	}

	public void setLevel(int level) {
		switch(level) {
			case 0: if (playScreen == null)
				marioBros = new MarioBros();
//			this.setScreen(marioBros);
				playScreen = new PlayScreen(marioBros);
				this.setScreen(playScreen);
			break;
		}
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();



	}
	
	@Override
	public void dispose () {

	}
}
