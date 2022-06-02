public class ClassTest {
    public static void main(String[] args) {
        Father f = new Son();
        f.print();
    }

}

class Father{
    int x = 10;
    Father(){
        this.print();
        x = 20;
    }
    public void print(){
        System.out.println(this.x);
    }

}
class Son extends Father{
    int x = 30;
    Son(){
        this.print();
        x = 40;
    }
    public void print(){
        System.out.println(x);
    }
}
