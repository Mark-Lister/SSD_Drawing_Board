package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
		recalculateRegion();
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
	}

	@Override
	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
		for (GObject object: gObjects) {
			object.move(dX, dY);
		}
	}
	
	public void recalculateRegion() {
		Integer minX = gObjects.get(0).x;
		Integer minY = gObjects.get(0).y;
		Integer maxX = minX + gObjects.get(0).width;
		Integer maxY =  minY + gObjects.get(0).height;

		for (GObject object: gObjects) {
			if(object.x < minX){
				minX = object.x;
			}
			if(object.y < minY){
				minY = object.y;
			}
			if(object.x + object.width > maxX){
				maxX = object.x + object.width;
			}
			if(object.y + object.height > maxY){
				maxY = object.y + object.height;
			}
		}
		this.x = minX;
		this.y = minY;
		this.width = maxX - minX;
		this.height = maxY - minY;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject object: gObjects) {
			object.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		for (GObject object: gObjects) {
			object.paintLabel(g);
		}
	}
	
}
