package com.iluwatar.value.object;

public class HeroStat {
    private int strength;               // Are you smart enough to be strength ?
    private int intelligence;           // Oh you are really smart ?
    private int luck;                   // Lucky shot.


    // Standard constructor.
    private HeroStat(int strength, int intelligence, int luck) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.luck = luck;
    }

    // Return OBJECT of this guy. Factory method.
    public static HeroStat valueOf(int strength, int intelligence, int luck) {
        return new HeroStat(strength, intelligence, luck);                  // Return new HeroStat object based on given
        // arguments.
    }


    // Getters.
    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getLuck() {
        return luck;
    }


    // About the object.

    // Values of current instance.
    @Override
    public String toString() {
        return "Strength: " + strength + ", intelligence: " + intelligence + ", luck: " + luck;
    }

    // HashCode of the instance.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (strength ^ (strength >>> 30));
        hash = 31 * hash + intelligence;
        hash = 31 * hash + luck;
        return hash;
    }

    // Equals method of the instance.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                               // If links of instances are the same they are equal.
        if (obj == null) return false;                              // If argument instance null instances are not equal.
        if (this.getClass() != obj.getClass()) return false;        // If classes of instances aren't the same
        HeroStat hero = (HeroStat) obj;                             // Get full representation of the argument instance.
        return this.hashCode() == hero.hashCode()                   // IF their values are the same instances are the equal.
                && intelligence == hero.intelligence
                && luck == hero.luck
                && strength == hero.strength;
    }
}