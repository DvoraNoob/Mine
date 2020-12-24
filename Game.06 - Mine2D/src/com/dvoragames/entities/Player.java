package com.dvoragames.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;
import com.dvoragames.world.Camera;
import com.dvoragames.world.World;


public class Player extends Entity{

	public static boolean moved;
	public boolean right,left,jump,down;
	private double speed = 0.6;
	
	public double life = 100;
	
	public boolean isJump;
	public double jumpHeight = 18;
	public double jumpTime = 0;
	public boolean isHold;

	public boolean timeLife;
	public int timeL = 0;
	
	public static int point = 0;
	
	public int d_none = 0, d_r = 1, d_l = 2, d_u = 3, d_d = 4,d_ur = 5,d_ul = 6;
	public int dir = d_none;
	
	private int frames = 0, maxFrames = 15,index = 0, maxIndex = 3;
	private int frames1 = 0, maxFrames1 = 20,index1 = 0, maxIndex1 = 1;

	private double gravity = 2;
	
	public BufferedImage[] playerStop;
	public BufferedImage[] playerR;
	public BufferedImage[] playerL;
	public BufferedImage[] playerU;
	public BufferedImage[] playerD;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		playerStop = new BufferedImage[2];
		playerR = new BufferedImage[4];
		playerL = new BufferedImage[4];
		
		for(int i = 0; i < 2; i++) {
			playerStop[i] = Game.spritesheet.getSprite(0 + (i*16), 80, 16, 16);
		}
		
		for(int i = 0; i < 4; i++) {
			playerR[i] = Game.spritesheet.getSprite(0 + (i*16), 96, 16, 16);
		}
		
		for(int i = 0; i < 4; i++) {
			playerL[i] = Game.spritesheet.getSprite(0 + (i*16), 112, 16, 16);
		}
	}
	
	public void tick(){
		depth = 2;
		moved = false;
		dir = d_none;
		if(World.isFree((int)x,(int)(y+gravity)) && isJump == false) {
			y+=gravity;
		}
		if(right && World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			dir = d_r;
			moved = true;
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = 2;
			dir = d_l;
			moved = true;
		}
		if(jump) {
			if(!World.isFree(this.getX(), this.getY()+1)) {
				isJump = true;
			}else {
				jump=false;
			}
		}
		
		if(isJump) {
			if(World.isFree(this.getX(), this.getY()-2) && isHold == true) {
				moved = false;
				dir = d_none;
				y-=2;
				jumpTime+=1;
				if(jumpTime == jumpHeight) {
					isJump = false;
					jump = false;
					jumpTime = 0;
				}
			}else {
				isJump = false;
				jump = false;
				jumpTime = 0;
			}
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}else if(!moved) {
			frames1++;
			if(frames1 == maxFrames1) {
				frames1 = 0;
				index1++;
				if (index1 > maxIndex1) {
					index1 = 0;
				}
			}
		}
		
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);
		
		
	}
	
	public void render(Graphics g){
		if(!moved) {
			if(dir == d_none) {
				g.drawImage(playerStop[index1], this.getX()-Camera.x, this.getY()-Camera.y, null);	
			}
		}else {
			if(dir == d_r) {
				g.drawImage(playerR[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
			}else if(dir == d_l) {
				g.drawImage(playerL[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
			}
		}
	}
}
