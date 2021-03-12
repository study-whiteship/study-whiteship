public class JavaSwitch {
    public static void main(String[] args) {
        System.out.println(getValueViaYield("a"));
        System.out.println(getValueViaYield("c"));
        System.out.println(getValueViaYield("e"));
        System.out.println(getValueViaYield("z"));
    }

    // Traditional switch
    private static int getValueBefore12(String mode) {
        int result;
        switch (mode) {
            case "a":
            case "b":
                result = 1;
                break;
            case "c":
                result = 2;
                break;
            case "d":
            case "e":
            case "f":
                result = 3;
                break;
            default:
                result = -1;
        }
        ;
        return result;
    }

    // Java 12, multiple comma-separated labels
    private static int getValueMultipleLabels(String mode) {
        int result;
        switch (mode) {
            case "a", "b":
                result = 1;
                break;
            case "c":
                result = 2;
                break;
            case "d", "e", "f":
                result = 3;
                break;
            default:
                result = -1;
        }
        ;
        return result;
    }

    // Java 13, value breaks are superseded by 'yield' statements
    // Java 12, switch expression returning value via break
    /*private static int getValueViaBreak(String mode) {
        int result = switch (mode) {
            case "a":
            case "b":
                break 1;
            case "c":
                break 2;
            case "d":
            case "e":
            case "f":
                break 3;
            default:
                break -1;
        };
        return result;
    }*/

    // Java 12, switch expression returns a value via label rules (arrow)
    private static int getValueViaArrow(String mode) {
        int result = switch (mode) {
            case "a", "b" -> 1;
            case "c" -> 2;
            case "d", "e", "f" -> {
                // do something here...
                System.out.println("Supports multi line block!");
                yield 3;
            }
            default -> -1;
        };
        return result;
    }

    // Java 13, switch expression returns a value via yield
    private static int getValueViaYield(String mode) {
        int result = switch (mode) {
            case "a", "b":
                yield 1;
            case "c":
                yield 2;
            case "d", "e", "f":
                // do something here...
                System.out.println("Supports multi line block!");
                yield 3;
            default:
                yield -1;
        };
        return result;
    }
}
