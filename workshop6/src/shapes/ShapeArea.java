package shapes;
@FunctionalInterface
public interface ShapeArea<T> {
    double getArea(T t);
}
