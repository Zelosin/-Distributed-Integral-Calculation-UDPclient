package zelosin.pack.Data;

public class GFG {
    static float y(float x){
        return 1 / (x);
    }
    public static float trapezoidal(float a, float b, float n){
        float h = (b - a) / n;
        float s = y(a) + y(b);

        for (int i = 1; i < n; i++)
            s += 2 * y( a + i * h);

        return (h / 2) * s;
    }
}
