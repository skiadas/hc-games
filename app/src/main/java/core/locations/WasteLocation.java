package core.locations;


public class WasteLocation implements Location {
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 2190389;
    }
}
