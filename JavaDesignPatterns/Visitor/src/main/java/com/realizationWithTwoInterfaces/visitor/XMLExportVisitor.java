package com.realizationWithTwoInterfaces.visitor;

import com.realizationWithTwoInterfaces.shapes.*;

public class XMLExportVisitor implements Visitor {                          /* I will name XMLExportVisitor class
                                                                            * and other classes which implements
                                                                            * Visitor interface as Visitor in context
                                                                            * of essential class. */
    public String export(Shape... shapes) {                                     /* Main method, which starts the
                                                                                * work with instances.*/
        StringBuilder resultOfShape = new StringBuilder();                      /* Instance for more convenient
                                                                                * change of strings.*/
        for (Shape shape : shapes) {
            resultOfShape.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");  /* Marks in file that we are
                                                                                            * considering new Shape instance.*/
            resultOfShape.append(shape.accept(this)).append("\n");                /* Adds result of shape base on this Visitor.*/
            System.out.println(resultOfShape.toString());                               /* Prints the result in console. */
            resultOfShape.setLength(0);                                                 /* Resume the result String.*/
        }
        return resultOfShape.toString();                                                /* Returns the final value.
                                                                                        * In this example it will be always
                                                                                        * empty String.*/
    }



    public String visitDot(Dot d) {                                             /* Code which will be executed when export()
                                                                                * method will contact with Shape implementation
                                                                                * of type Dot.*/
        return "<dot>" + "\n" +
                "    <id>" + d.getId() + "</id>" + "\n" +
                "    <x>" + d.getX() + "</x>" + "\n" +
                "    <y>" + d.getY() + "</y>" + "\n" +
                "</dot>";
    }


    public String visitCircle(Circle c) {                                       /* Code which will be executed when export()
                                                                                 * method will contact with Shape implementation
                                                                                 * of type Circle.*/
        return "<circle>" + "\n" +
                "    <id>" + c.getId() + "</id>" + "\n" +
                "    <x>" + c.getX() + "</x>" + "\n" +
                "    <y>" + c.getY() + "</y>" + "\n" +
                "    <radius>" + c.getRadius() + "</radius>" + "\n" +
                "</circle>";
    }


    public String visitRectangle(Rectangle r) {                                 /* Code which will be executed when export()
                                                                                 * method will contact with Shape implementation
                                                                                 * of type Rectangle.*/
        return "<rectangle>" + "\n" +
                "    <id>" + r.getId() + "</id>" + "\n" +
                "    <x>" + r.getX() + "</x>" + "\n" +
                "    <y>" + r.getY() + "</y>" + "\n" +
                "    <width>" + r.getWidth() + "</width>" + "\n" +
                "    <height>" + r.getHeight() + "</height>" + "\n" +
                "</rectangle>";
    }


    public String visitCompoundGraphic(CompoundShape cg) {                      /* Code which will be executed when export()
                                                                                 * method will contact with Shape implementation
                                                                                 * of type CompoundShape.*/
        return "<compound_graphic>" + "\n" +
                "   <id>" + cg.getId() + "</id>" + "\n" +
                _visitCompoundGraphic(cg) +                                     /* Creates XML representation of all Shapes allocated
                                                                                * in CompoundShape instance. */
                "</compound_graphic>";
    }

    private String _visitCompoundGraphic(CompoundShape compoundShape) {
        StringBuilder resultOfCompoundShape = new StringBuilder();           // Result String of CompoundShape instance.
        for (Shape shape : compoundShape.childrenShapes) {
            String resultOfShape = shape.accept(this);                // XML representation of the Shape.
            resultOfShape = "    " + resultOfShape.replace("\n", "\n    ") + "\n";  // Proper indentation for sub-objects.
            resultOfCompoundShape.append(resultOfShape);                    // Adding refactored Shape instance.
        }
        return resultOfCompoundShape.toString();                            // Returning the result.
    }

}
