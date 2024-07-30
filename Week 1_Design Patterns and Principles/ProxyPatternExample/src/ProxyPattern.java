public class ProxyPattern {

    // Define Subject Interface
    interface Image {
        void display();
    }

    // Implement Real Subject Class
    static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromServer();
        }

        private void loadFromServer() {
            // Simulate loading image from a remote server
            System.out.println("Loading image: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // Implement Proxy Class
    static class ProxyImage implements Image {
        private RealImage realImage;
        private String filename;
        private boolean isImageLoaded;

        public ProxyImage(String filename) {
            this.filename = filename;
            this.isImageLoaded = false;
        }

        @Override
        public void display() {
            if (!isImageLoaded) {
                realImage = new RealImage(filename);
                isImageLoaded = true;
            }
            realImage.display();
        }
    }

    
    public static void main(String[] args) {
        // Create a proxy image
        Image image1 = new ProxyImage("photo1.jpg");

        // The image is not loaded yet
        System.out.println("First request:");
        image1.display();

        // The image is now loaded and cached
        System.out.println("Second request:");
        image1.display();

        // Create another proxy image
        Image image2 = new ProxyImage("photo2.jpg");
        System.out.println("Third request:");
        image2.display();
    }
}

//OUTPUT
// First request:
// Loading image: photo1.jpg
// Displaying image: photo1.jpg
// Second request:
// Displaying image: photo1.jpg
// Third request:
// Loading image: photo2.jpg
// Displaying image: photo2.jpg