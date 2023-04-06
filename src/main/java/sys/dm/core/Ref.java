package sys.dm.core;

public class Ref<T> {
    private T value;

    public Ref(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T anotherValue) {
        value = anotherValue;
    }

    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (value == null) {
            return obj == null;
        }
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }
}