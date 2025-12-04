package core.actions;
import core.locations.Location; // import the interface so that we can use the different locations

public class SelectAction implements Action {
    private Location location;

    public SelectAction(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
