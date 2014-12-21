/**
 * Created by GuoJunjun on 21.12.14.
 * <p/>
 * Generic Class
 */
public class Pair<T, S> {
    private T first;
    private S second;

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static void main(String[] args) {
        String [] names = {"Ole", "Kari", "Johan"};
        Pair<String,Integer> result = firstContaining(names, "a");
        System.out.println(result.getFirst());
        System.out.println(result.getSecond());
    }

    private static Pair<String, Integer> firstContaining(String[] names, String a) {
        for (int i = 0; i < names.length; i++) {
            if(names[i].contains(a)){
                return new Pair<String, Integer>(names[i], i);
            }
        }
        return new Pair<String, Integer>(null, -1);
    }
}
