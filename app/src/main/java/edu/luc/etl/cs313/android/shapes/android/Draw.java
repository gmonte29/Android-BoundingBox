package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)

    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas; // FIXME
        this.paint = paint; // FIXME
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) {
        int pc=c.getColor();
        paint.setColor(c.getColor());
        c.getShape().accept(this);
        paint.setColor(pc);
         return null;
    }

    @Override
    public Void onFill(final Fill f) {
        Style sty = paint.getStyle();
        paint.setStyle(Style.FILL_AND_STROKE);
        f.getShape().accept(this);
        paint.setStyle(sty);
        return null;
    }

    @Override
    public Void onGroup(final Group g) {

        for(Shape s : g.getShapes())
        {
            s.accept(this);
        }
        return null;
    }

    @Override
    public Void onLocation(final Location l) {
        Shape s=l.getShape();
        canvas.translate(l.getX(),l.getY());
        s.accept(this);
        canvas.translate(-l.getX(),-l.getY());

        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {
        canvas.drawRect(0,0,r.getWidth(),r.getHeight(),paint);
        return null;
    }

    @Override
    public Void onOutline(Outline o) {
        Style sty=paint.getStyle();
        paint.setStyle(Style.STROKE);
        o.getShape().accept(this);
        paint.setStyle(sty);
        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {
        final float[] pts = new float[4*s.getShapes().size()];
        int i=0;
        for(Point point: s.getPoints()){
            do {   pts[i++] = point.getX();
                   pts[i++] = point.getY();
               }while((i/2)%2==0);
        }
        pts[i]=s.getPoints().get(0).getX();
        pts[++i]=s.getPoints().get(0).getY();
        canvas.drawLines(pts,paint);
        return null;
    }
}
