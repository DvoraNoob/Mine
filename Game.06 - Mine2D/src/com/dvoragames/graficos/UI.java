package com.dvoragames.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.dvoragames.main.Game;

public class UI {

	public void render(Graphics g) {
		int curLife = (int) ((Game.player.life/100)*200);
		g.setColor(Color.red);
		g.fillRect(50, 15, 200, 20);
		g.setColor(Color.green);
		g.fillRect(50, 15, curLife, 20);
	}
	
}
