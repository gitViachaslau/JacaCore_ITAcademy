package processors;

public class Processor {
    private Manufacturer manufacturer;
    private String model;
    private int frequency;

    public Processor(Manufacturer manufacturer, String model, int frequency) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.frequency = frequency;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "manufacturer=" + manufacturer +
                ", model='" + model + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
