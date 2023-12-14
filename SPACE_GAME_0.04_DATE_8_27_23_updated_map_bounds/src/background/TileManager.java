package background;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager 
{
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap();
		
	}
	
	
	public void getTileImage()
	{
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/space0.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/space1.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/space2.png"));
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/space3.png"));
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/space4.png"));
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/space5.png"));
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/space6.png"));
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/space7.png"));
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/space8.png"));
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/space9.png"));
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void loadMap()
	{
		try
		{
			Random rand = new Random();
			int col = 0; 
			int row = 0;
			int last = 11;
			int current = 12;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				while(col < gp.maxWorldCol)
				{
					while(last == current)
					{
						current = rand.nextInt(10);
						mapTileNum[col][row] = current;

					}
					last = current;
					col++;
					
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
		}
		catch(Exception e) 
		{
			
		}
	}
	
	public void draw(Graphics2D g2)
	{
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = (int) (worldX - gp.hero.getX() + gp.hero.screenX);
			int screenY = (int) (worldY - gp.hero.getY() + gp.hero.screenY);
			
			if(worldX + gp.tileSize*2 > gp.hero.x - gp.hero.screenX &&
			   worldX - gp.tileSize*2 < gp.hero.x + gp.hero.screenX &&
			   worldY + gp.tileSize*2 > gp.hero.y - gp.hero.screenY &&
			   worldY - gp.tileSize*2 < gp.hero.y + gp.hero.screenY)
			{
			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, gp);
			}
			
			worldCol++;

			
			if(worldCol == gp.maxWorldCol)
			{
				worldCol = 0;

				worldRow++;

			}
		}
		
		
	}

}
