public class EnumTest {
    enum Week {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static void main(String[] args) {
        Week today = Week.SUNDAY;
        String name = today.name();
        System.out.println(name);

        int ordinal = today.ordinal();
        System.out.println(ordinal);

        if (args.length == 1) {
            String day = args[0];
            Week weekDay = Week.valueOf(day);

            if (weekDay == Week.SATURDAY || weekDay == Week.SUNDAY) {
                System.out.println("It's Weekdays!");
            } else {
                System.out.println("It's Weekend!");
            }
        }

        Week[] days = Week.values();
        for (Week day : days) {
            System.out.println(day);
        }
    }
}
