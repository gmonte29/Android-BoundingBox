package edu.luc.etl.cs313.android.shapes.model;
import android.graphics.Rect;

import java.util.*;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {
       return f.getShape().accept(this);

    }

    @Override
    public Location onGroup(final Group g) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(Shape s : g.getShapes()){
            Location temp = s.accept(this);

            Rectangle r = (Rectangle) temp.getShape();

            int lrY = r.height + temp.getY();
            int lrX = r.width + temp.getX();

            if(temp.getX() < minX) minX = temp.getX();
            if(temp.getY() < minY) minY = temp.getY();

            if(lrX > maxX) maxX = lrX;
            if(lrY > maxY) maxY = lrY;
        }

        int height = maxY-minY;
        int width = maxX-minX;

        return new Location(minX, minY, new Rectangle(width,height));
    }

    @Override
    public Location onLocation(final Location l) { //GM: updated to make scalable

        Location s = l.getShape().accept(this);
        return new Location(l.getX()+s.getX(), l.getY()+s.getY(), s.getShape());
    }

    @Override
    public Location onRectangle(final Rectangle r) {
        final int width = r.getWidth();
        final int height = r.getHeight();
        return new Location(-0, -0, new Rectangle(width, height));
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {
        return c.getShape().accept(this);
    }

    @Override
    public Location onOutline(final Outline o) {
        return o.getShape().accept(this);

    }

    @Override
    public Location onPolygon(final Polygon s) { //GM: updated onPolygon to be scalable
        List<? extends  Point> list = s.getPoints();

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getX() > maxX) maxX = list.get(i).getX();
            if(list.get(i).getX() < minX) minX = list.get(i).getX();
            if(list.get(i).getY() > maxY) maxY = list.get(i).getY();
            if(list.get(i).getY() < minY) minY = list.get(i).getY();
        }

        int height = maxY-minY;
        int width = maxX-minX;

        return new Location(minX, minY, new Rectangle(width, height));
    }
}
