import enums.Event;

public class Main2 {
    public static void main(String[] args) {
        for (Event event : Event.values()) {
            System.out.println(event.ordinal() + 1 + ". " + event);
        }
        int eventChoice = 2;
        System.out.println(Event.values()[eventChoice - 1]);
    }
}
