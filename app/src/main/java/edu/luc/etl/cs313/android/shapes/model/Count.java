package edu.luc.etl.cs313.android.shapes.model;

import java.util.Collections;
import java.util.Objects;

/**
 * A visitor to compute the number of basic shapes in a (possibly complex)
 * shape.
 */
public class Count implements Visitor<Integer> {

    // TODO entirely your job

    @Override
    public Integer onPolygon(final Polygon p) {
        return 1;
    }

    @Override
    public Integer onCircle(final Circle c) {
        return 1;
    }

    @Override


    public Integer onGroup(final Group g) {
        Integer counter=0;

        for(Shape s : g.getShapes())
        {
            if(!(s==null))
            {counter= counter + s.accept(this);}
            else   counter++;
        }
        return counter;
    }

    @Override
    public Integer onRectangle(final Rectangle q) {
        return 1;
    }

    @Override
    public Integer onOutline(final Outline o) {
        return o.getShape().accept(this);
    }

    @Override
    public Integer onFill(final Fill c) {
        return c.getShape().accept(this);
    }

    @Override
    public Integer onLocation(final Location l) {return l.getShape().accept(this); }

    @Override
    public Integer onStrokeColor(final StrokeColor c) {
        return c.getShape().accept(this);
    }
}
