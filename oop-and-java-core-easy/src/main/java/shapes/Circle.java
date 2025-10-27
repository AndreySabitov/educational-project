package shapes;

public class Circle extends Shape {
    private int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(r, 2);
    }

    @Override
    public void draw() {
        System.out.println("Рисую круг");
    }
}
