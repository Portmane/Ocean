public class test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        sb.append("HELLO ").append("ONETWOTHREE\n");
        System.out.println(sb.toString());
    }
}
