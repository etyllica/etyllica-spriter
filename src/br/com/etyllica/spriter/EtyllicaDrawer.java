package br.com.etyllica.spriter;

import java.awt.Color;

import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Loader;
import com.brashmonkey.spriter.Timeline;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;

public class EtyllicaDrawer extends Drawer<ImageLayer> {

	int width;
	int height;
	Graphics g;

	/**
	 * Creates a new drawer based on the given loader.
	 *
	 * @param loader the loader containing resources
	 */
	public EtyllicaDrawer(int width, int height, Loader<ImageLayer> loader) {
		super(loader);
		this.width = width;
		this.height = height;
	}

	@Override
	public void setColor(float r, float g, float b, float a) {
		this.g.setColor(new Color(r, g, b, a));
	}

	@Override
	public void line(float x1, float y1, float x2, float y2) {
		g.drawLine(x1, height-y1, x2, height-y2);
	}

	@Override
	public void rectangle(float x, float y, float width, float height) {
		g.drawRect(x, height-y, width, height);
	}

	@Override
	public void circle(float x, float y, float radius) {
		g.drawCircle(x, height-y, radius);
	}

	@Override
	public void draw(Timeline.Key.Object object) {
		ImageLayer layer = loader.get(object.ref);
		float newPivotX = (layer.utilWidth() * object.pivot.x);
		float newX = object.position.x - newPivotX;
		float newPivotY = (layer.utilHeight() * object.pivot.y)-layer.utilHeight();
		float newY = object.position.y - newPivotY;

		layer.setX((int)newX);
		//Inverted Y
		layer.setY((int)(height-newY));
		layer.setOrigin(newPivotX, -newPivotY);

		float angle = -object.angle;

		if ((object.scale.y >= 0 && object.scale.x < 0)
		|| (object.scale.y < 0 && object.scale.x >= 0)) {
				angle = -angle;
		}

		layer.setAngle(angle);

		layer.setOpacity((int)(255*object.alpha));
		layer.setScaleX(object.scale.x);
		layer.setScaleY(object.scale.y);
		layer.draw(g);
	}

	public Graphics getGraphics() {
		return g;
	}

	public void setGraphics(Graphics g) {
		this.g = g;
	}
}
