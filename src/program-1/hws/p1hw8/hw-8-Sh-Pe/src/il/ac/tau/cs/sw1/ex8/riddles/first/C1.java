package il.ac.tau.cs.sw1.ex8.riddles.first;

import java.util.Random;

public class C1 {
    
    private static String str = "secret";

    public String foo() {
        str += Integer.toString(new Random().nextInt(10));
        return str;
    }

    public static void main(String[] args) {
        str = args[0];
        C1 c = new C1();
        A1 a = new B1();
        if (c.foo().equals(a.foo())) {
            System.out.println("success!");
        }
    }
}
