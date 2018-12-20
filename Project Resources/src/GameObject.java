import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javafx.util.Pair;

/**
 * 
 */

/**
 * @author Jasper
 *
 */
public class GameObject {
	
	private Dimension dim; //Dimensions of object
	private Point coords; //Top left corner of object
	private Image objectImage; //test for a single image object
	private boolean clickable;
	private boolean isVisible;
	
	public void setProperties(Dimension dimIn, Point posIn, Image imageIn) {
		this.dim = dimIn;
		this.coords = posIn;
		this.objectImage = imageIn;
	}
	
	public void setProperties(Dimension dimIn, Point posIn, Image imageIn, boolean clickable) {
		this.dim = dimIn;
		this.coords = posIn;
		this.objectImage = imageIn;
		this.clickable = clickable;
	}
	
	public Pair<Dimension,Point> getPosition(){
		Pair<Dimension,Point> newPos = new Pair<Dimension,Point>(dim,coords);
		return newPos;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.objectImage, coords.x, coords.y, null);
		
	}
	
	public boolean isClickable(){
		return clickable;
	}
	
	public boolean isVisible(){
		return isVisible;
	}
	
	public void setVisible(boolean isVisible){
		this.isVisible = isVisible;
	}
}