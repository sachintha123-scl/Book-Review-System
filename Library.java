import java.util.Scanner;

public class Library {
    private final BookBST catalog = new BookBST();
    private final MyQueue<Review> recentActivity = new MyQueue<>(5);
    private final MyStack<Book> recentlyViewed = new MyStack<>();
    private final Scanner scanner = new Scanner(System.in);
    private int nextId = 1;

    public void run() {
        seedSampleData();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:  displayAllBooks(); break;
                case 2:  addBook(); break;
                case 3:  addReview(); break;
                case 4:  searchById(); break;
                case 5:  searchByTitle(); break;
                case 6:  showRecentActivity(); break;
                case 7:  viewBookReviews(); break;
                case 8:  showRecentlyViewed(); break;
                case 9:  deleteBook(); break;
                case 0:  running = false; System.out.println("\nGoodbye — happy reading!"); break;
                default: System.out.println("Invalid option. Please choose a number from the menu.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===================== BOOK REVIEW & RECOMMENDATION SYSTEM =====================");
        System.out.println(" 1. Display All Books");
        System.out.println(" 2. Add a Book");
        System.out.println(" 3. Add a Review to a Book");
        System.out.println(" 4. Search Book by ID");
        System.out.println(" 5. Search Books by Title");
        System.out.println(" 6. Recent Activity");
        System.out.println(" 7. View Reviews of a Book");
        System.out.println(" 8. View Recently Viewed Books");
        System.out.println(" 9. Delete a Book");
        System.out.println(" 0. Exit");
        System.out.println("=================================================================================");
    }

    private void addBook() {
        System.out.println("\n-- Add New Book --");
        String title = readLine("Title: ");
        String author = readLine("Author: ");
        String genre = readLine("Genre: ");
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and Author cannot be empty. Book was not added.");
            return;
        }
        Book book = new Book(nextId, title, author, genre.isEmpty() ? "Unspecified" : genre);
        catalog.insert(book);
        System.out.println("Added: " + book);
        nextId++;
    }

    private void addReview() {
        System.out.println("\n-- Add Review --");
        int id = readInt("Enter Book ID: ");
        Book book = catalog.search(id);
        if (book == null) { System.out.println("No book found with ID " + id + "."); return; }

        int rating = readInt("Rating (1-5): ");
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5. Review discarded.");
            return;
        }
        String text = readLine("Review text: ");
        if (text.isEmpty()) text = "(no comment)";

        Review review = new Review(rating, text, book.getTitle());
        book.addReview(review);
        recentActivity.enqueue(review);
        recentlyViewed.push(book);
        System.out.println("Review added to \"" + book.getTitle() + "\".");
    }

    private void viewBookReviews() {
        System.out.println("\n-- View Book Reviews --");
        int id = readInt("Enter Book ID: ");
        Book book = catalog.search(id);
        if (book == null) { System.out.println("No book found with ID " + id + "."); return; }

        MyLinkedList<Review> reviews = book.getReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews yet for \"" + book.getTitle() + "\".");
        } else {
            System.out.println("Reviews for \"" + book.getTitle() + "\":");
            for (Review r : reviews) {
                System.out.println("  " + r);
            }
        }
        recentlyViewed.push(book);
    }

    private void searchById() {
        int id = readInt("\nEnter Book ID to search: ");
        Book book = catalog.search(id);
        if (book == null) { System.out.println("No book found with ID " + id + "."); return; }
        System.out.println("Found: " + book);
        recentlyViewed.push(book);
    }

    private void searchByTitle() {
        String keyword = readLine("\nEnter a title keyword: ");
        if (keyword.isEmpty()) { System.out.println("Please enter a non-empty keyword."); return; }
        MyLinkedList<Book> matches = catalog.searchByTitle(keyword);
        if (matches.isEmpty()) { System.out.println("No books match \"" + keyword + "\"."); return; }
        System.out.println("Matches:");
        for (Book b : matches) {
            System.out.println("  " + b);
            recentlyViewed.push(b);
        }
    }

    private void displayAllBooks() {
        MyLinkedList<Book> all = catalog.inorder();
        if (all.isEmpty()) { System.out.println("\nCatalog is empty. Add a book first (option 2)."); return; }
        System.out.println("\n-- All Books (sorted by ID via BST in-order traversal) --");
        for (Book b : all) System.out.println("  " + b);
    }

    private void showRecentActivity() {
        System.out.println("\n-- Recent Activity Feed (last " + recentActivity.size() + " review(s), oldest first) --");
        if (recentActivity.isEmpty()) { System.out.println("  No reviews recorded yet."); return; }
        for (Review r : recentActivity.toList()) System.out.println("  " + r);
    }

    private void showRecentlyViewed() {
        System.out.println("\n-- Recently Viewed Books (most recent first) --");
        if (recentlyViewed.isEmpty()) { System.out.println("  No navigation history yet."); return; }
        for (Book b : recentlyViewed.toList()) System.out.println("  " + b.getId() + ": " + b.getTitle());

        String ans = readLine("Delete the most recently viewed book? (y/n): ");
        if (ans.equalsIgnoreCase("y")) {
            try {
                Book popped = recentlyViewed.pop();
                System.out.println("Popped: " + popped.getTitle());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteBook() {
        int id = readInt("\nEnter Book ID to delete: ");
        boolean removed = catalog.delete(id);
        System.out.println(removed ? "Book " + id + " deleted from the catalog." : "No book found with ID " + id + ".");
    }

    private void seedSampleData() {
        String[][] samples = {
            {"The Hobbit", "J.R.R. Tolkien", "Fantasy"},
            {"Dune", "Frank Herbert", "Sci-Fi"},
            {"1984", "George Orwell", "Dystopian"},
            {"Foundation", "Isaac Asimov", "Sci-Fi"}
        };
        for (String[] s : samples) {
            Book b = new Book(nextId, s[0], s[1], s[2]);
            catalog.insert(b);
            nextId++;
        }
        System.out.println("Sample catalog loaded (4 books, IDs 1-4). Choose option 1 to view them.");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
