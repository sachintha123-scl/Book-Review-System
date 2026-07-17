public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private final MyLinkedList<Review> reviews = new MyLinkedList<>();

    Book left, right;

    public Book(int id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public MyLinkedList<Review> getReviews() { return reviews; }

    public void addReview(Review r) { reviews.addLast(r); }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        int total = 0, count = 0;
        for (Review r : reviews) { total += r.getRating(); count++; }
        return (double) total / count;
    }

    @Override
    public String toString() {
        return String.format("ID:%-4d | %-28s | %-18s | %-12s | Avg: %.1f/5 (%d review%s)",
                id, title, author, genre, getAverageRating(), reviews.size(),
                reviews.size() == 1 ? "" : "s");
    }
}
