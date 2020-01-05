package com.realizationWithTwoInterfaces.shapes;

import com.realizationWithTwoInterfaces.visitor.Visitor;

public interface Shape {                    // Interface of all figures.
    void move(int x, int y);                // Move to X and Y values.

    void draw();                            // Representation of the figure.

    String accept(Visitor visitor);         /* Gives us an ability to access the figure and work with it through third
                                            * part class(Visitor). */
                                            // Method of the Visitor design pattern.

}
