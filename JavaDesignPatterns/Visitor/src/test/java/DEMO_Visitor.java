import com.realizationWithTwoInterfaces.shapes.*;
import com.realizationWithTwoInterfaces.visitor.XMLExportVisitor;

public class DEMO_Visitor {
    public static void main(String[] args) {

        realizationWithTwoInterfaces.execute();     /* Stats execution of the realization of Visitor Java pattern
                                                    * allocated in realizationWithTwoInterfaces package.*/
    }



    static class iluwatar {
        public static void execute() {

        }
    }

    static class realizationWithTwoInterfaces {
        public static void execute() {
            Dot dot = new Dot(1, 10, 55);               // Creating Dot instance.
            Circle circle = new Circle(2, 23, 15, 10);  // Creating Circle instance.
            Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30); // Creating Rectangle instance.

            CompoundShape compoundShape = new CompoundShape(4); // Creating CompoundShape instance.
            compoundShape.add(dot);         // Adding Dot instance.
            compoundShape.add(circle);      // ...
            compoundShape.add(rectangle);   // ...

            CompoundShape childCompoundShape = new CompoundShape(5); // New instance of CompoundShape.
            childCompoundShape.add(dot);     // Adds Shape of type Dot.
            compoundShape.add(childCompoundShape);      // Adds CompoundShape instance in List of another CompoundShape.

            export(circle, compoundShape);              // Runs a programme.
        }

        private static void export(Shape... shapes) {
            XMLExportVisitor exportVisitor = new XMLExportVisitor();        // Creating one of Visitor classes.
            System.out.print(exportVisitor.export(shapes));                 // Starts Visiting instances which are given as arguments.
        }
    }
}
