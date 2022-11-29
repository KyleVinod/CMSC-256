package cmsc256;

public class MyBook implements Comparable<MyBook>{
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    public MyBook(){
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        if (authorFirstName == null) {
            throw new IllegalArgumentException();
        }
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        if (authorLastName == null) {
            throw new IllegalArgumentException();
        }
        this.authorLastName = authorLastName;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        if ((ISBN10.length() != 10) || !(ISBN10.matches("[0-9]+"))) {
            throw new IllegalArgumentException();
        }
        this.ISBN10 = ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        if ((ISBN13.length() != 13) || !(ISBN13.matches("[0-9]+"))) {
            throw new IllegalArgumentException();
        }
        this.ISBN13 = ISBN13;
    }

    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + authorFirstName + " " + authorLastName + "\nISBN10: " + ISBN10 + "\nISBN13: " + ISBN13;
    }
    public boolean equals(Object otherBook) {
        if (this == otherBook) {
            return true;
        }
        if (!(otherBook instanceof MyBook)) {
            return false;
        }
        else {
            MyBook other = (MyBook) otherBook;
            if (((MyBook)otherBook).ISBN10.equals(ISBN10)) {
                return true;
            }
            else {
                return ((MyBook)otherBook).ISBN13.equals(ISBN13);
            }
        }
    }
    public int compareTo(MyBook other) {
        int num = authorLastName.compareTo(other.authorLastName);
        if (num == 0) {
            num = authorFirstName.compareTo(other.authorFirstName);
        }
        if (num == 0) {
            num = title.compareTo(other.title);
        }
        return num;
    }
}
