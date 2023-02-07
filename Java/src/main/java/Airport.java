import com.jasur.epam.planes.ExperimentalPlane;
import com.jasur.epam.models.ClassificationLevel;
import com.jasur.epam.models.MilitaryType;
import com.jasur.epam.planes.MilitaryPlane;
import com.jasur.epam.planes.PassengerPlane;
import com.jasur.epam.planes.Plane;

import java.util.*;

public class Airport {
    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    private <T extends Plane> List<T> getPlanesInstancesOfClass(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (Plane plane : planes) {
            if (clazz.isInstance(plane))  result.add(clazz.cast(plane));
        }
        return result;
    }

    private List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType militaryType) {
        List<MilitaryPlane> militaryPlanesOfSpecificType = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane militaryPlane : militaryPlanes) {
            if (militaryPlane.getType() == militaryType) militaryPlanesOfSpecificType.add(militaryPlane);
        }
        return militaryPlanesOfSpecificType;
    }

    private List<ExperimentalPlane> getExperimentalPlanesByClassificationLevel(ClassificationLevel level) {
        List<ExperimentalPlane> experimentalPlanesOfSameClassificationLevel = new ArrayList<>();
        List<ExperimentalPlane> experimentalPlanes = getExperimentalPlanes();
        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == level)
                experimentalPlanesOfSameClassificationLevel.add(experimentalPlane);
        }
        return experimentalPlanesOfSameClassificationLevel;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanesInstancesOfClass(MilitaryPlane.class);
    }

    public List<PassengerPlane> getPassengerPlane() {
        return getPlanesInstancesOfClass(PassengerPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanesInstancesOfClass(ExperimentalPlane.class);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.BOMBER);
    }

    public List<ExperimentalPlane> getUnclassifiedExperimentalPlanes() {
        return getExperimentalPlanesByClassificationLevel(ClassificationLevel.UNCLASSIFIED);
    }

    public List<ExperimentalPlane> getSecretExperimentalPlanes() {
        return getExperimentalPlanesByClassificationLevel(ClassificationLevel.SECRET);
    }

    public List<ExperimentalPlane> getTopSecretExperimentalPlanes() {
        return getExperimentalPlanesByClassificationLevel(ClassificationLevel.TOP_SECRET);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public boolean isNextPlaneMaxLoadCapacityIsHigherThanCurrent(){
        List<? extends Plane> planesSortedByMaxLoadCapacity = sortByMaxLoadCapacity().getPlanes();
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxLoadCapacity() < nextPlane.getMaxLoadCapacity()) {
                return true;
            }
        }
        return false;
    }
    public Airport sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    private void print(Collection<? extends Plane> collection) {
        for (Plane plane : collection) {
            System.out.println(plane);
        }
    }
    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
