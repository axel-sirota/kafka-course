package com.honolulu;

class Intensity {
    public static Intensity newBuilder() {
        return new Intensity();
    }
    public Intensity() {
    }

    public Intensity(String scale, Integer measurement) {
        this.scale = scale;
        this.measurement = measurement;
    }

    private String scale;
    private Integer measurement;

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Integer getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Integer measurement) {
        this.measurement = measurement;
    }
}

public class DisasterValue {

    public DisasterValue(Intensity intensity, String disasterType) {
        this.intensity = intensity;
        this.disasterType = disasterType;
    }

    public DisasterValue() {
    }

    public static DisasterValue newBuilder() {
        return new DisasterValue();
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public DisasterValue setIntensity(Intensity intensity) {
        this.intensity = intensity;
        return this;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public DisasterValue setDisasterType(String disasterType) {
        this.disasterType = disasterType;
        return this;
    }

    private Intensity intensity;
    private String disasterType;

    @Override
    public String toString() {
        return "DisasterValue{" +
                "intensity='" + intensity + '\'' +
                ", disasterType='" + disasterType + '\'' +
                '}';
    }
}
