package com.dvoragames.main;

import java.awt.Color;
import java.awt.Graphics;

import com.dvoragames.world.Camera;
import com.dvoragames.world.FloorTile;
import com.dvoragames.world.Tile;
import com.dvoragames.world.WallTile;
import com.dvoragames.world.World;

public class Inventory {
	
	public int inventorySize = 40;
	public int selected = 0;
	
	public static boolean isPlace;
	public static boolean isPressed;
	public static int mx, my;
	
	public String[] itens = {"grama","terra",""};
	
	public int initialP = ((Game.WIDTH*Game.SCALE)/2) - ((itens.length*inventorySize)/2);
	
	public void tick() {
		if(isPressed) {
			isPressed = false;

			if(mx >= initialP && mx < initialP + (inventorySize*itens.length)) {
				if(my >= Game.HEIGHT*Game.SCALE-inventorySize && my < Game.HEIGHT*Game.SCALE-inventorySize + inventorySize) {
					selected = (int)(mx-initialP)/inventorySize;
				}
			}
		}
		
		if(isPlace) {
			isPlace = false;
			
			mx = (int)mx/3 + Camera.x;
			my = (int)my/3 + Camera.y;
			
			int tilex = mx/16;
			int tiley = my/16;
			
			if(World.tiles[tilex+tiley*World.WIDTH].solid == false) {
				if(itens[selected] == "grama") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_GRASS);
				}else if(itens[selected] == "terra") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16,tiley*16,Tile.TILE_DIRT);
				}else if(itens[selected] == "") {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_AIR);
				}
				
				if(World.isFree(Game.player.getX(), Game.player.getY()) == false) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16,tiley*16,Tile.TILE_AIR);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < itens.length; i++) {
			g.setColor(Color.gray);
			g.fillRect(initialP+(i*inventorySize), Game.HEIGHT*Game.SCALE-inventorySize, inventorySize, inventorySize);
			g.setColor(Color.black);
			g.drawRect(initialP+(i*inventorySize), Game.HEIGHT*Game.SCALE-inventorySize, inventorySize, inventorySize);
			
			if(itens[i] == "grama") {
				g.drawImage(Tile.TILE_GRASS, initialP+(i*inventorySize)+4, Game.HEIGHT*Game.SCALE-inventorySize+4, 32, 32,null);
			}else if(itens[i] == "terra") {
				g.drawImage(Tile.TILE_DIRT, initialP+(i*inventorySize)+4, Game.HEIGHT*Game.SCALE-inventorySize+4, 32, 32,null);
			}else if(itens[i] == "") {
				g.drawImage(null, initialP+(i*inventorySize)+4, Game.HEIGHT*Game.SCALE-inventorySize+4, 32, 32,null);
			}
			
			if(selected == i) {
				g.setColor(Color.yellow);
				g.drawRect(initialP+(i*inventorySize), Game.HEIGHT*Game.SCALE-inventorySize, inventorySize-1, inventorySize-1);
			}
		}
	}

}
