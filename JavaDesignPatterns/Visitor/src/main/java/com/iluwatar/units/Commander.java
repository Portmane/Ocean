package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Commander extends Unit{
    Commander() {                               // Standard constructor.
        super();
    }
    Commander(Unit[] units) {
        super(units);
    }


    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitCommander(this);       /* When UnitVisitor implementation will be given to
                                                * this class instance, it will call implemented
                                                * visitCommander() method in given UnitVisitor
                                                * interface using META DATA of this class instance.*/
    }
}
