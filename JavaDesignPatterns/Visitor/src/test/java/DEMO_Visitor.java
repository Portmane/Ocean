import com.iluwatar.units.Commander;
import com.iluwatar.units.Sergeant;
import com.iluwatar.units.Soldier;
import com.iluwatar.units.Unit;
import com.iluwatar.visitors.CommanderVisitor;
import com.iluwatar.visitors.SergeantVisitor;
import com.iluwatar.visitors.SoldierVisitor;
import com.iluwatar.visitors.UnitVisitor;
import com.realizationWithTwoInterfaces.shapes.*;
import com.realizationWithTwoInterfaces.visitor.XMLExportVisitor;

public class DEMO_Visitor {
    public static void main(String[] args) {

        realizationWithTwoInterfaces.execute();     /* Stats execution of the realization of the Visitor Java pattern
                                                    * allocated in realizationWithTwoInterfaces package.*/
        iluwatar.execute();                         /* Starts execution of the realization of the Visitor Java pattern
                                                    * allocated in iluwatar package.*/
    }



    static class iluwatar {
        public static void execute() {
            Unit unit = new Unit();                             // Creating Unit instance.
            Commander commander = new Commander();              // Creating Commander instance.
            Sergeant sergeant = new Sergeant();                 // Creating Sergeant instance.
            Soldier soldier = new Soldier();                    // Creating Soldier instance.

            Unit[] arrayOfUnits = {unit, commander, sergeant, soldier};       // Putting all our instances in one array.
            export(arrayOfUnits);
        }

        private static void export(Unit[]... units) {             // Starts visiting our array of instances.

            UnitVisitor[] arrayOfUnitVisitors = {new CommanderVisitor(),
                    new SergeantVisitor(),
                    new SoldierVisitor()};                          // Creating all available Visitors.


            for (Unit[] unitArray : units) {                        // Gets access to the array of Units.
                int whichVisitorIsExecuting = 0;                            /* Variable, which give information, which
                                                                            * Visitor is executing from arrayOfUnitVisitors array. */
                for (Unit unit : unitArray) {                               // Gets one of Units.
                    System.out.println("Visitor NUMBER: " + whichVisitorIsExecuting);
                    whichVisitorIsExecuting++;
                    for (UnitVisitor unitVisitor : arrayOfUnitVisitors) {   // Gets one of Visitors.
                        unit.accept(unitVisitor);                           /* Making visit to the Unit instance using one of
                                                                            * Visitors.*/
                    }
                }
            }
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
            childCompoundShape.add(dot);                 // Adds Shape of type Dot.
            compoundShape.add(childCompoundShape);      // Adds CompoundShape instance in List of another CompoundShape.

            export(circle, compoundShape);              // Runs a programme.
        }

        private static void export(Shape... shapes) {
            XMLExportVisitor exportVisitor = new XMLExportVisitor();        // Creating one of Visitor classes.
            System.out.print(exportVisitor.export(shapes));                 // Starts Visiting instances which are given as arguments.
        }
    }
}
