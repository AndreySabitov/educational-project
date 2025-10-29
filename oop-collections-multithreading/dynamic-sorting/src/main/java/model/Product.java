package model;

public class Product implements Comparable<Product> {
    private String name;
    private double cost;
    private double weight;

    public Product(String name, double cost, double weight) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
    }

    @Override
    public int compareTo(Product o) {
        return Double.compare(this.cost, o.cost);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}
