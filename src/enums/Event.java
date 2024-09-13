package enums;

public enum Event {
    BIRTHDAY(1.2f),
    CONFERENCE(1.5f),
    MEETING(1.3f),
    NONE(1.0f);
    
    private final float rate;

    Event(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public static Event fromString(String text) {
        for (Event event : Event.values()) {
            if (event.toString().equalsIgnoreCase(text)) {
                return event;
            }
        }
        return null;
    }
}
