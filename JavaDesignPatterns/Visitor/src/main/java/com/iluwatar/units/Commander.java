package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Commander extends Unit{
    public Commander() {                               // Standard constructor.
        super();
    }
    public Commander(Unit[] units) {
        super(units);
    }


    @Override
    public void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitCommander(this);       /* When UnitVisitor implementation will be given to
                                                * this class instance, it will call implemented
                                                * visitCommander() method in given UnitVisitor
                                                * interface using META DATA of this class instance.*/
    }
}
