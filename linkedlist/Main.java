public class Main {
        public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("SUKI");
        list.add("IKUS");
        list.add("JUALIL");
        list.display();

        list.delete("IKUS");
        list.display();

        list.delete("JUALIL");
        list.display();

        list.add("ETHYL");
        list.addFirst("ABSOLUTE");
        list.display();

        list.clear();
        list.display();
    }
}

