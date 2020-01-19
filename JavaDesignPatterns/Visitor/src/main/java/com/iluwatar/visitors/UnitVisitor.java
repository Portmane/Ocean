package com.iluwatar.visitors;

import com.iluwatar.units.Commander;
import com.iluwatar.units.Sergeant;
import com.iluwatar.units.Soldier;

public interface UnitVisitor {
    void visitSoldier(Soldier soldier);         // Method of Soldier visit.
    void visitSergeant(Sergeant sergeant);       // Method of Sergeant visit.
    void visitCommander(Commander commander);   // Method of Commander visit.
}
