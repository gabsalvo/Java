import java.util.HashMap;
import java.util.Map;

public class RomanToInteger{
    private static final Map<Character, Integer> map;

    static {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
    }


    public static void main (String[] args) {
        System.out.println(romanToInt("VI"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("XIX"));
    }

      public static int romanToInt(String s) {
        int result = 0;
        int prevValue = 0;

        for (char c : s.toCharArray()) {
            int value = map.get(c);

            if (value > prevValue) {
                result += value - 2 * prevValue;
            } else {
                result += value;
            }

            prevValue = value;
        }

        return result;
    }
} 
