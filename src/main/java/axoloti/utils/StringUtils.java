package axoloti.utils;

/**
 *
 * @author jtaelman
 */
public class StringUtils {

    public static String denullString(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }

}
