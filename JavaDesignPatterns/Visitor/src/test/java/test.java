public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        sb.append("HELLO ").append("ONETWOTHREE\n");
        System.out.println(sb.toString());


        String str = "<dot>" + "\n" +
                "<id>1</id>" + "\n" +
                "<x>10</x>" + "\n" +
                "<y>55</y>" + "\n" +
                "</dot>";
        System.out.println(str.replace("\n", "\n    "));
//        System.out.println("   " + str);
        var sb1 = new StringBuilder("HELLO");
        System.out.print(sb1);
    }
}
