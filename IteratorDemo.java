import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {

    public static void main(String[] args)
    {
        ArrayList al = new ArrayList();
        // add elements to the array list
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");

        // print list
        Iterator it = al.iterator();
        while(it.hasNext())
        {
            System.out.print(it.next() + " ");
        }
    }
}
