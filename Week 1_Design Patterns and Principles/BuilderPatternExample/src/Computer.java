public class Computer {

    // Attributes
    private String CPU;
    private String RAM;
    private String Storage;
    private String GPU;
    private String OS;

    // Private constructor to be used by the Builder class
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
        this.OS = builder.OS;
    }

    // Static nested Builder class
    public static class Builder {
        // Required parameters
        private final String CPU;
        private final String RAM;

        // Optional parameters
        private String Storage = "256GB SSD";
        private String GPU = "Integrated";
        private String OS = "Windows 10";

        // Builder constructor with required parameters
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        // Methods to set optional parameters
        public Builder setStorage(String storage) {
            this.Storage = storage;
            return this;
        }

        public Builder setGPU(String gpu) {
            this.GPU = gpu;
            return this;
        }

        public Builder setOS(String os) {
            this.OS = os;
            return this;
        }

        // Build method to create a Computer instance
        public Computer build() {
            return new Computer(this);
        }
    }

    
    public static void main(String[] args) {
        // Creating a Computer with default optional parameters
        Computer computer1 = new Computer.Builder("Intel i7", "16GB").build();
        System.out.println("Computer 1: CPU=" + computer1.CPU + ", RAM=" + computer1.RAM +
                           ", Storage=" + computer1.Storage + ", GPU=" + computer1.GPU + ", OS=" + computer1.OS);

        // Creating a Computer with custom optional parameters
        Computer computer2 = new Computer.Builder("AMD Ryzen 9", "32GB")
                                  .setStorage("1TB SSD")
                                  .setGPU("NVIDIA RTX 3080")
                                  .setOS("Ubuntu 20.04")
                                  .build();
        System.out.println("Computer 2: CPU=" + computer2.CPU + ", RAM=" + computer2.RAM +
                           ", Storage=" + computer2.Storage + ", GPU=" + computer2.GPU + ", OS=" + computer2.OS);
    }
}

//OUTPUT
// Computer 1: CPU=Intel i7, RAM=16GB, Storage=256GB SSD, GPU=Integrated, OS=Windows 10
// Computer 2: CPU=AMD Ryzen 9, RAM=32GB, Storage=1TB SSD, GPU=NVIDIA RTX 3080, OS=Ubuntu 20.04