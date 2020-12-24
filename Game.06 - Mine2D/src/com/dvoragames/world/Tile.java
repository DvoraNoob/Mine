package com.dvoragames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRASS = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_DIRT = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_ROCK = Game.spritesheet.getSprite(32,0,16,16);
	public static BufferedImage TILE_SAND = Game.spritesheet.getSprite(48,0,16,16);
	public static BufferedImage TILE_AIR = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_SOLIDROCK = Game.spritesheet.getSprite(16,16,16,16);

	private BufferedImage sprite;
	private int x,y;
	
	public boolean solid = false;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
