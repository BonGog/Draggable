import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 
 */

/**
 * @author Jasper
 * 
 */
public class IsometricTile extends WorldObject{
	public Point isoPos;
	public boolean walkable;
	public boolean slave;
	public Point masterLocation;
	protected Structure structureOnTile = null;
	public enum TILESET{
		grass,
		water,
		trees,
		city,
	}
	public TILESET tileset;
	/**
	 * @param type
	 * @param worldDimsIn
	 * @param worldPointIn
	 */
	public IsometricTile(ObjectType type, Dimension worldDimsIn, Point worldPointIn, TILESET tileset, Point tilePos) {
		super(type, worldDimsIn, worldPointIn);
		this.tileset = tileset;
		this.isoPos = tilePos.getLocation();
		this.clickable = true;
		this.clickTag = "tile";
		if (this.tileset == TILESET.grass) {
			this.walkable = true;
		} else {
			this.walkable = false;
		}
		
	}
	public void setStructureOnTile(Structure structureOnTile) {
		this.structureOnTile = structureOnTile;
		
	}
	//Assigns a tile as the master tile for this one, actions on this tile will be executed on master
//	public void setMaster(Point masterPoint){
//		if(masterPoint != null) {
//			this.slave = true;
//			this.masterLocation = new Point(masterPoint);
//		}
//
//	}
//	public Point getMaster(){
//		return this.masterLocation;
//	}
//	public void setSlave(boolean isSlave) {
//		this.slave = isSlave;
//	}
//	public IsometricTile(ObjectType type, Dimension worldDimsIn, Point worldPointIn, TILESET tileset, Point tilePos, Point masterPoint) {
//		super(type, worldDimsIn, worldPointIn);
//		this.tileset = tileset;
//		this.isoPos = tilePos.getLocation();
//		this.clickable = true;
//		this.clickTag = "tile";
//		this.walkable = false;
//
//		
//	}
	
	public Point getIsoPoint() {
		return this.isoPos;
	}
	
	public void changeTileset(TILESET tilesetIn) {
		this.tileset = tilesetIn;
		if(tilesetIn == TILESET.water) {
			this.objectImage = "watertile0";
		}
		if(tilesetIn == TILESET.grass) {
			this.objectImage = "grasstile0";
		}
		if(tilesetIn == TILESET.trees) {
			this.objectImage = "treestile0";
		}
	}
	@Override
	public void hoverAction() {
//		System.out.println("tile hovered at:" + this.isoPos);
		this.currentlyHovered = true;
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Game.objectMap.getImage(objectImage), coords.x + Game.xOffset, coords.y + Game.yOffset, null);
		if(currentlyHovered) {
			g.drawImage(Game.objectMap.getImage("hover"), coords.x + Game.xOffset, coords.y + Game.yOffset, null);
		}
	}
}
