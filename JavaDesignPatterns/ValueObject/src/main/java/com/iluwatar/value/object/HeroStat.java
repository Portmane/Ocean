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
    // Return object of this guy. Factory method.
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
        return super.hashCode();
    }

    // Equals method of the instance.
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}