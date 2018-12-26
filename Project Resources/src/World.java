import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import javafx.util.Pair;

/**
 *
 */

/**
 * @author Jasper
 *
 */
public class World {

	// panelDims = dimensions of the main display (the portion of the world currently being rendered)
	// panelPoint = true location of the top left corner of the main display - (100, 100) relative to the ContentPane
	// worldDims = dimensions of the entire world, including parts not currently being rendered
	// worldPoint = the point within the world which is currently corresponding to panelPoint
	public Dimension panelDims;
	public Point panelPoint;
	public Dimension worldDims;
	public Point worldPoint;
	public Point staticWorldPoint;
//	public Map<String,Image> imageAssetMap;


	World(Dimension worldDims)  {
		panelDims = new Dimension(Game.width-200,Game.height-200);
		panelPoint = new Point(100,100);
		worldPoint = new Point(100,100);
		this.worldDims = worldDims;

	}

	/**
	 * creates a worldobject, laods its image from the map and adds it to the object map. Returns true if succesful
	 * @return
	 */
	public boolean newTileObject() {
		return false;
	}
	

	public void initialiseTileMap() {
		
		Point nextTileWorldCoords = new Point(-2500,-1000);
		System.out.println(nextTileWorldCoords.x);
		BufferedReader br = null;
		String line = "";
		String delim = ",";
		
//		int tileX = 600;
//		int tileY = 600;
//		int tileCount = 0;
			try {
				br = new BufferedReader(new FileReader("tilemap.csv"));
				int j = 0;
				while((line = br.readLine()) != null){
					String[] tileLine = line.split(delim);
					
					IsometricTile.TILESET tileType = null;
					for(int i = 0; i < tileLine.length; i++) {

						if(tileLine[i].compareTo("g") == 0) {
							tileType = IsometricTile.TILESET.grass;
						}else if(tileLine[i].compareTo("w") == 0){
							tileType = IsometricTile.TILESET.water;
						}
						if(tileType != null) {
							Game.objectMap.addWorldTile(nextTileWorldCoords,tileType);
							System.out.println(nextTileWorldCoords);
							nextTileWorldCoords = new Point(nextTileWorldCoords.x + 32, nextTileWorldCoords.y);
						}


//								Random randomNum = new Random();
//								int rn = randomNum.nextInt(3);
//								IsometricTile testTile = new IsometricTile(ObjectType.WORLD,new Dimension(64,32),mainGameRenderer.toIsometric(new Point(tileX,tileY)),IsometricTile.TILESET.grass);
//								
//								String tileName = "grasstile"+Integer.toString(rn);
//								testTile.setProperties(new Dimension(64,32),new Point(900,900),tileName,false);
//								objectMap.addWorldObject(tileID, testTile);
//								tileCount++;
//							}
//							tileY = tileY + 32;
//							tileX = 600;
//						}
//							
//						}
					}
//					System.out.println();
					nextTileWorldCoords = new Point(0, nextTileWorldCoords.y+32);
					j++;
				}
				br.close();
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		
	}

	/**
	 *  Repositions every object in the main display. Called after the display is offset
	 */
	public void updateDisplay() {
		Game.objectMap.updateMainDisplayObjects();

		for(Map.Entry<String,WorldObject> obj : Game.objectMap.getMainDisplayObjects().entrySet()) {
			obj.getValue().setPosition(worldPoint,panelPoint);
		}

	}
	public Pair<Dimension,Point> getMainDisplayCoords() {
		return new Pair<Dimension,Point>(this.panelDims,this.panelPoint);
	}


	/**
	 * This function is called by InputHandler when the mouse is dragged, it moves the display view of the world.
	 *
	 * @param mousePressPos : the position the mouse was pressed in, used as the world anchor for offsetting display
	 * @param mousePos : the position of the mouse when function is called
	 */
	public void offsetDisplay(Point mousePressPos, Point mousePos) {
		if(this.staticWorldPoint == null) {
			this.staticWorldPoint = this.worldPoint.getLocation();
		}

		this.worldPoint.y = staticWorldPoint.y + ((( mousePressPos.y - mousePos.y)) );
		this.worldPoint.x = staticWorldPoint.x + ((( mousePressPos.x - mousePos.x)) );
		if(!withinBounds(this.worldPoint)) {
			if(worldPoint.y < 0) {
				worldPoint.y = 0;
			}
			if(worldPoint.x < 0) {
				worldPoint.x = 0;
			}
			if(worldPoint.x + panelDims.width > worldDims.width) {
				worldPoint.x = worldDims.width-panelDims.width;
			}
			if(worldPoint.y + panelDims.height > worldDims.height) {
				worldPoint.y = worldDims.height-panelDims.height;
			}


		}

		updateDisplay();

	}


	public void generateWorld() {


//		try {
//			Game.mainGameRenderer.semaphore.acquire();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Image testIso = new ImageIcon("assets/iso1.png").getImage();
//		for(int i = 0; i < 5; i++) {
//			for(int j = 0; j < 5; i++) {
//				WorldObject testIsoObj = new WorldObject(ObjectType.WORLD, new Dimension(0,0), new Point(700,700));
//				testIsoObj.setProperties(new Dimension(70,40), new Point(700,700), testIso);
//				Game.objectMap.addWorldObject(Integer.toString(i+j),testIsoObj);
//			}
//		}
//
//
//		Game.mainGameRenderer.semaphore.release();
	}

	public boolean withinBounds(Point worldPointIn) {
		if(worldPointIn.y > 0 && worldPointIn.x > 0 && worldPointIn.x+panelDims.width < worldDims.width && worldPointIn.y+panelDims.height < worldDims.height) {
			return true;
		}
		return false;
	}

}
