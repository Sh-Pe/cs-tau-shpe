package il.ac.tau.cs.sw1.ex8.riddles.second;

import java.util.Random;

public class C2 {

    public static void main(String[] args) {
        String input = args[0];
        int num = new Random().nextInt(10);
        B2 b = new B2();
        A2 a = b.getA(num);
        String str = input;
        for (int i = 0; i < num; i++) {
            str += input;
        }
        if(!input.equals(a.foo(str))) {
            return;
        }
        System.out.println("success!");
    }
}
