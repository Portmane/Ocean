package com.realizationWithTwoInterfaces.visitor;

import com.realizationWithTwoInterfaces.shapes.Circle;
import com.realizationWithTwoInterfaces.shapes.CompoundShape;
import com.realizationWithTwoInterfaces.shapes.Dot;
import com.realizationWithTwoInterfaces.shapes.Rectangle;

public interface Visitor {                              // Interface for visitor classes realization.
    String visitDot(Dot dot);                           // Gives access to Dot instance.

    String visitCircle(Circle circle);                  // Gives access to Circle instance.

    String visitRectangle(Rectangle rectangle);         // Gives access to Rectangle instance.

    String visitCompoundGraphic(CompoundShape cg);      // Gives access to CompoundShape instance.
}
