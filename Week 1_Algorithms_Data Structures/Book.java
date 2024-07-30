import java.util.Arrays;

public class Book {
    String bookId;
    String title;
    String author;

    // Constructor
    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    // Linear search method to find books by title
    public static Book linearSearch(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null; // Not found
    }

    // Binary search method to find books by title (assuming sorted array)
    public static Book binarySearch(Book[] books, String targetTitle) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(targetTitle);

            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }

    
    public static void main(String[] args) {
        // Sample books with Indian examples
        Book[] books = {
            new Book("1", "The White Tiger", "Aravind Adiga"),
            new Book("2", "A Fine Balance", "Rohinton Mistry"),
            new Book("3", "The God of Small Things", "Arundhati Roy")
        };

        // Perform linear search
        String searchTitle = "A Fine Balance";
        Book foundBookLinear = linearSearch(books, searchTitle);
        if (foundBookLinear != null) {
            System.out.println("Linear Search Found: " + foundBookLinear.title + " by " + foundBookLinear.author);
        } else {
            System.out.println("Linear Search: Book not found.");
        }

        // Sort books by title for binary search
        Arrays.sort(books, (a, b) -> a.title.compareToIgnoreCase(b.title));

        // Perform binary search
        Book foundBookBinary = binarySearch(books, searchTitle);
        if (foundBookBinary != null) {
            System.out.println("Binary Search Found: " + foundBookBinary.title + " by " + foundBookBinary.author);
        } else {
            System.out.println("Binary Search: Book not found.");
        }
    }
}

//OUTPUT
// Linear Search Found: A Fine Balance by Rohinton Mistry
// Binary Search Found: A Fine Balance by Rohinton Mistry