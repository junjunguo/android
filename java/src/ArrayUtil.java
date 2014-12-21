/**
 * Created by GuoJunjun on 21.12.14.
 */
public class ArrayUtil {
    /**
     * a Generic Method print out parameter type
     *
     * @param a
     * @param <T>
     */
    // <T> : supply the type variable before the return type
    public static <T> void print(T[] a) {
        System.out.println("Generic method:");
        for (T t : a) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }

    /**
     * @param a
     *         String list
     *         <p/>
     *         Method print out a string list
     */
    public static void print(String[] a) {
        for (String s : a) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(new String[]{"Ole", "kari"});
        String[] s = {"Ole", "kari", "Nordmann"};
        print(s);
        Integer [] i = {1,2,3,4};
        print(i);
        Double [] d = {1.1,0.1,1.98,2.9,3.99,5.0};
        print(d);
    }
}
