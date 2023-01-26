package proiect2;

public class Test {
    public static void main(String args[]) throws Exception {
        Gramatica g = new Gramatica("C:\\Users\\Administrator\\Facultate\\LFC\\proiect2\\Ex3.txt");
        g.lema3();
        g.lema1();
        g.lema2();
        System.out.println(g.toString());
    }
}
