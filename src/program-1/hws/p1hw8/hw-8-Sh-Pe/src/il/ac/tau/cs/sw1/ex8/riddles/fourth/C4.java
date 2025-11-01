package il.ac.tau.cs.sw1.ex8.riddles.forth;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class C4 {
    public static void main(String[] args){
        int randNum = new Random().nextInt(10)+1;
        A4 a = new A4(args, randNum);
        List<String> lst = new ArrayList<String>();
        for (String s : a){
            lst.add(s);
        }
        for (int i = 0; i < args.length; i++){
            for (int j = 0; j < randNum; j++){
                if (!lst.get(i+args.length*j).equals(args[i])){
                    return;
                }
            }
        }
        System.out.println("success!");
    }
}
