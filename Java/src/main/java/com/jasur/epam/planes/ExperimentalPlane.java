package com.jasur.epam.planes;

import com.jasur.epam.models.ClassificationLevel;
import com.jasur.epam.models.ExperimentalTypes;

public class ExperimentalPlane extends Plane{
    private final ExperimentalTypes type;
    private final ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalTypes type, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public ExperimentalTypes getType() {return type;}

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ExperimentalPlane{" +
                getStringRepresentationOfPlaneProperties() +
                "type=" + type +
                ", classificationLevel=" + classificationLevel +
                '}';
    }

}
