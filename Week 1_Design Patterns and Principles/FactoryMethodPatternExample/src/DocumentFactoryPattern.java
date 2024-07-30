public class DocumentFactoryPattern {

    // Define Document interface
    interface Document {
        void open();
    }

    // Concrete classes implementing Document
    static class WordDocument implements Document {
        //Override
        public void open() {
            System.out.println("Opening a Word document.");
        }
    }

    static class PdfDocument implements Document {
        //Override
        public void open() {
            System.out.println("Opening a PDF document.");
        }
    }

    static class ExcelDocument implements Document {
        //Override
        public void open() {
            System.out.println("Opening an Excel document.");
        }
    }

    // Abstract DocumentFactory class
    abstract static class DocumentFactory {
        abstract Document createDocument();
    }

    // Concrete factories
    static class WordDocumentFactory extends DocumentFactory {
        //Override
        Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory {
        //Override
        Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        //Override
        Document createDocument() {
            return new ExcelDocument();
        }
    }

    
    public static void main(String[] args) {
        // Create factories
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Create documents using factories
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        // Open documents
        wordDoc.open();
        pdfDoc.open();
        excelDoc.open();
    }
}

//OUTPUT
// Opening a Word document.
// Opening a PDF document.
// Opening an Excel document.