import com.iluwatar.value.object.HeroStat;

public class test {
        public static void main(String[] args) {
            var heroStatInstance = HeroStat.valueOf(10, 1, 8);       // Creating our instance using factory.
            System.out.println(heroStatInstance.toString());                                // Information about instance.
        }
}
