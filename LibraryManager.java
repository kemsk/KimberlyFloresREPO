import java.util.ArrayList;
import java.util.List;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
    String getType();
    String getDetails();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean borrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("Book '" + title + "' has been borrowed.");
        } else {
            System.out.println("Book '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("Book '" + title + "' has been returned.");
        } else {
            System.out.println("Book '" + title + "' was not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String getType() {
        return "Book";
    }

    @Override
    public String getDetails() {
        return title + " by " + author + " (" + publicationYear + ")";
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!borrowed) {
            borrowed = true;
            System.out.println("DVD '" + title + "' has been borrowed.");
        } else {
            System.out.println("DVD '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println("DVD '" + title + "' has been returned.");
        } else {
            System.out.println("DVD '" + title + "' was not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String getType() {
        return "DVD";
    }

    @Override
    public String getDetails() {
        return title + " directed by " + director + " (" + runningTime + " minutes)";
    }
}

abstract class LibraryUser {
    protected String name;
    protected List<LibraryItem> borrowedItems;

    public LibraryUser(String name) {
        this.name = name;
        borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.contains(item)) {
            item.returnItem();
            borrowedItems.remove(item);
        } else {
            System.out.println("Item was not borrowed by this user.");
        }
    }

    public int getTotalBorrowedBooks() {
        int count = 0;
        for (LibraryItem item : borrowedItems) {
            if (item instanceof Book) {
                count++;
            }
        }
        return count;
    }

    public int getTotalBorrowedDVDs() {
        int count = 0;
        for (LibraryItem item : borrowedItems) {
            if (item instanceof DVD) {
                count++;
            }
        }
        return count;
    }

    public abstract String getUserType();

    public String getName() {
        return name;
    }
}

class Student extends LibraryUser {
    public Student(String name) {
        super(name);
    }

    @Override
    public String getUserType() {
        return "Student";
    }
}

class Teacher extends LibraryUser {
    public Teacher(String name) {
        super(name);
    }

    @Override
    public String getUserType() {
        return "Teacher";
    }
}

public class LibraryManager {
    public static void main(String[] args) {
        List<LibraryUser> users = new ArrayList<>();
        users.add(new Student("Rio"));
        users.add(new Student("bub"));
        users.add(new Teacher("Caroline"));
        users.add(new Teacher("Bingo"));

        List<LibraryItem> items = new ArrayList<>();
        items.add(new Book("Introduction to Java", "John Smith", 2020));
        items.add(new Book("Data Structures and Algorithms", "Jane Doe", 2018));
        items.add(new DVD("The Matrix", "The Wachowskis", 136));
        items.add(new DVD("Inception", "Christopher Nolan", 148));
        items.add(new DVD("Rio", "Nolan", 130));
        items.add(new Book("I love you since 1892", "Udegor", 2016));

        // Assign borrowed items
        users.get(0).borrowItem(items.get(0));
        users.get(0).borrowItem(items.get(2));
        users.get(1).borrowItem(items.get(1));
        users.get(2).borrowItem(items.get(3));
        users.get(3).borrowItem(items.get(5));
        users.get(3).borrowItem(items.get(4));
        users.get(1).borrowItem(items.get(5));


        // Display borrowed items for each user
        for (LibraryUser user : users) {
            System.out.println("------------------------------------------");
            System.out.println(user.getUserType() + ": " + user.getName());
            System.out.println("Borrowed:");
            for (LibraryItem item : user.borrowedItems) {
                System.out.println(item.getType() + ": " + item.getDetails());
            }
            System.out.println("------------------------------------------");
        }
    }
}
