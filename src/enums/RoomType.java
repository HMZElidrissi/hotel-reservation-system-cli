package enums;

public enum RoomType {
    SINGLE(1),
    DOUBLE(1.5f),
    SUITE(2.5f);

    private final float rate;

    RoomType(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public static RoomType fromString(String text) {
        for (RoomType roomType : RoomType.values()) {
            if (roomType.name().equalsIgnoreCase(text)) {
                return roomType;
            }
        }
        return null;
    }
}
