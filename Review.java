public class Review {
    private final int rating;
    private final String text;
    private final String bookTitle;

    public Review(int rating, String text, String bookTitle) {
        this.rating = rating;
        this.text = text;
        this.bookTitle = bookTitle;
    }

    public int getRating() { return rating; }
    public String getText() { return text; }
    public String getBookTitle() { return bookTitle; }

    @Override
    public String toString() {
        return "[" + bookTitle + "] " + rating + "/5 stars - \"" + text + "\"";
    }
}
