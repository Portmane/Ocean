package com.realizationWithTwoInterfaces.visitor;

import com.realizationWithTwoInterfaces.shapes.Circle;
import com.realizationWithTwoInterfaces.shapes.CompoundShape;
import com.realizationWithTwoInterfaces.shapes.Dot;
import com.realizationWithTwoInterfaces.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
