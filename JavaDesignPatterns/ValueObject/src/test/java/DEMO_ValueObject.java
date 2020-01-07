import com.iluwatar.value.object.HeroStat;

public class DEMO_ValueObject {
        public static void main(String[] args) {
            var instanceOfTheHeroStatClassFirst = HeroStat.valueOf(10, 1, 8);            // Creating our instance using factory.
            System.out.println(instanceOfTheHeroStatClassFirst.toString());                                     // Displaying information about our instance.

            var instanceOfTheHeroStatClassSecond = HeroStat.valueOf(1, 1, 1);           // Creating second instance.


            System.out.println("Hash code of the first instance: " + instanceOfTheHeroStatClassFirst.hashCode());       // Is hashcode() method works right ?
            System.out.println("Hash code of the second instance: " + instanceOfTheHeroStatClassSecond.hashCode());     // Is hashcode() method works right ?


            var instanceOfTheHeroStatClassThird = HeroStat.valueOf(1, 1, 1);                 // Third instance.
            var instanceOfTheHeroStatClassFourth = instanceOfTheHeroStatClassFirst;                                 // Fourth instance has the same link on the object as the first instance.
            System.out.println("Does the first instance equal to second instance ?: " + instanceOfTheHeroStatClassFirst.equals(instanceOfTheHeroStatClassSecond));      // First instance is not equal
            // to second.
            System.out.println("Does the second instance equal to third instance ? The values of both instances are the same: " + instanceOfTheHeroStatClassThird
                    .equals(instanceOfTheHeroStatClassSecond));                                                         // Second instance is equal to the third. Because hash is the same as the
            // values of the variables.
            System.out.println("Does the first instance equal to fourth instance ? The values of both instances are the same: " + instanceOfTheHeroStatClassFirst
                    .equals(instanceOfTheHeroStatClassFourth));                                                     // First instance is equal with fourth instance.
            // They are referring on the same cell of memory.
        }
}
