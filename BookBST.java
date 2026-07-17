public class BookBST {
    private Book root;
    private int count;

    public int size() { return count; }

    public boolean insert(Book book) {
        if (search(book.getId()) != null) return false;
        root = insertRec(root, book);
        count++;
        return true;
    }

    private Book insertRec(Book node, Book book) {
        if (node == null) return book;
        if (book.getId() < node.getId()) node.left = insertRec(node.left, book);
        else node.right = insertRec(node.right, book);
        return node;
    }

    public Book search(int id) {
        Book cur = root;
        while (cur != null) {
            if (id == cur.getId()) return cur;
            cur = (id < cur.getId()) ? cur.left : cur.right;
        }
        return null;
    }

    public MyLinkedList<Book> searchByTitle(String keyword) {
        MyLinkedList<Book> results = new MyLinkedList<>();
        searchByTitleRec(root, keyword.toLowerCase(), results);
        return results;
    }

    private void searchByTitleRec(Book node, String keyword, MyLinkedList<Book> results) {
        if (node == null) return;
        searchByTitleRec(node.left, keyword, results);
        if (node.getTitle().toLowerCase().contains(keyword)) results.addLast(node);
        searchByTitleRec(node.right, keyword, results);
    }

    public MyLinkedList<Book> inorder() {
        MyLinkedList<Book> results = new MyLinkedList<>();
        inorderRec(root, results);
        return results;
    }

    private void inorderRec(Book node, MyLinkedList<Book> results) {
        if (node == null) return;
        inorderRec(node.left, results);
        results.addLast(node);
        inorderRec(node.right, results);
    }

    public boolean delete(int id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        count--;
        return true;
    }

    private Book deleteRec(Book node, int id) {
        if (node == null) return null;
        if (id < node.getId()) { node.left = deleteRec(node.left, id); return node; }
        if (id > node.getId()) { node.right = deleteRec(node.right, id); return node; }

        if (node.left == null) return node.right;
        if (node.right == null) return node.left;

        Book successor = findMin(node.right);
        Book replacement = new Book(successor.getId(), successor.getTitle(),
                successor.getAuthor(), successor.getGenre());
        for (Review r : successor.getReviews()) replacement.addReview(r);
        replacement.left = node.left;
        replacement.right = deleteRec(node.right, successor.getId());
        return replacement;
    }

    private Book findMin(Book node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
