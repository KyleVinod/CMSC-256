package cmsc256;

/**
 * Kyle Vinod
 * 2/17/22
 * CMSC 256 Tues/Thurs
 * Project 2
 */
public class RamString implements WackyStringInterface{
    // Setting all instances variables
    private String ram;
    // Default constructor
    public RamString(){
        ram = "Rodney, the Ram";
    }
    // Parameterized constructor
    public RamString(String ram){
        if (ram == null) {
            throw new IllegalArgumentException();
        }
        this.ram = ram;
    }
    // Setter method
    public void setWackyString(String ram) {
        if (ram == null) {
            throw new IllegalArgumentException();
        }
        this.ram = ram;
    }
    // Getter method
    public String getWackyString(){
        return ram;
    }

    @Override
    public String getEveryFifthCharacter() {
        String fifth = "";
        int num = 4;
        // This determines if the string has at least 5 letters
        if (this.ram.length() >= 5) {
            fifth += this.ram.charAt(num);
        }
        else {
            return fifth;
        }
        // Determines the letters that are the fifth character
        for (int i = num + 5; i < this.ram.length(); i += 5) {
            fifth += this.ram.charAt(i);
        }
        return fifth;
    }

    @Override
    public String getEvenCharacters() {
        String even = "";
        // Since the first position starts at 0, if "i % 2 == 1" then it's even
        for (int i = 0; i < this.ram.length(); i++) {
            if (i % 2 == 1) {
                even += String.valueOf(this.ram.charAt(i));
            }
        }
        return even;
    }

    @Override
    public String getOddCharacters() {
        // Since the first position starts at 0, if "i % 2 == 0" then it's odd
        String odd = "";
        for (int i = 0; i < this.ram.length(); i++) {
            if (i % 2 == 0) {
                odd += String.valueOf(this.ram.charAt(i));
            }
        }
        return odd;
    }

    @Override
    public int countDoubleDigits() {
        int dub = 0;
        for (int i = 0; i < this.ram.length(); i++) {
            // Determines if the letter is a number
            switch (this.ram.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                if (i < this.ram.length() - 1) {
                    // Determines if it's the first position, and then determines if the position after is the same
                    if (i == 0) {
                        if (this.ram.charAt(i) == this.ram.charAt(i + 1) && this.ram.charAt(i + 2) == ' ') {
                            dub++;
                        }
                    }
                    // Determines if it's the second last position, and then if the position after is the same
                    else if (i == this.ram.length() - 2) {
                        if (this.ram.charAt(i) == this.ram.charAt(i + 1) && this.ram.charAt(i - 1) == ' ') {
                            dub++;
                        }
                    }
                    // Determines if only the position after is the same
                    else {
                        if (this.ram.charAt(i) == this.ram.charAt(i + 1) && this.ram.charAt(i - 1) == ' ' && this.ram.charAt(i + 2) == ' ') {
                            dub++;
                        }
                    }
                }
                break;
            }
        }
        return dub;
    }

    @Override
    public int countVowels() {
        int vow = 0;
        for (int i = 0; i < this.ram.length(); i++) {
            // If the letter is any of the letters below, then it adds to vow
            switch (this.ram.charAt(i)) {
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                case 'Y':
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    vow++;
            }
        }
        return vow;
    }

    @Override
    public String reformatName() {
        // Determines if there's a " " anywhere in the string, and if not then it's a single word
        int words = this.ram.indexOf(" ");
        if (words == -1) {
            throw new UnsupportedOperationException();
        }
        String name = ", ";
        String newName = "";
        int num = 0;
        // Getting the first word, and it already begins with a comma
        for (int i = 0; this.ram.charAt(i) != ' '; i++) {
            name += this.ram.charAt(i);
        }
        // Getting the second word
        for (int i = 0; i < this.ram.length(); i++) {
            if (this.ram.charAt(i) == ' ' && num == 0) {
                for (int j = i + 1; j < this.ram.length(); j++) {
                    newName += this.ram.charAt(j);
                }
                num++;
            }
        }
        if (this.ram.length() > 1) {
            newName += name;
        }
    return newName;
    }

    @Override
    public void ramifyString() {
        String ram = "";
        for (int i = 0; i < this.ram.length(); i++) {
            // If at first position, then it determines if it's only one 0, two 0 next to each other, or no 0
                if (i == 0) {
                    if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) == '0' && this.ram.charAt(i + 2) != '0') {
                        ram += "CS@VCU";
                        i++;
                    }
                    else if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) != '0') {
                        ram += "Go VCU";
                    }
                    else {
                        ram += this.ram.charAt(i);
                    }
                }
                // Last position and determines if it's one 0 or no 0
                else if (i == this.ram.length() - 1) {
                    if (this.ram.charAt(i) == '0' && this.ram.charAt(i - 1) != '0') {
                        ram += "Go VCU";
                    }
                    else {
                        ram += this.ram.charAt(i);
                    }
                }
                // At second last position, and determines if it's one 0, two 0 next to each other, or no 0
                else if (i == this.ram.length() - 2) {
                    if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) == '0' && this.ram.charAt(i - 1) != '0') {
                        ram += "CS@VCU";
                        i++;
                    }
                    else  if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) != '0' && this.ram.charAt(i - 1) != '0') {
                        ram += "Go VCU";
                    }
                    else  {
                        ram += this.ram.charAt(i);
                    }
                }
                // Determines anywhere in the string if there's one 0, two 0 next to each other, or no 0
                else {
                    if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) == '0' && this.ram.charAt(i - 1) != '0' && this.ram.charAt(i + 2) != '0') {
                        ram += "CS@VCU";
                        i++;
                    }
                    else if (this.ram.charAt(i) == '0' && this.ram.charAt(i + 1) != '0' && this.ram.charAt(i - 1) != '0') {
                        ram += "Go VCU";
                    }
                    else {
                        ram += this.ram.charAt(i);
                    }
                }
        }
        this.ram = ram;
    }

    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException {
        String newString = "";
        // Throws IllegalArgumentException if the startPosition is bigger than endPosition
        if (startPosition > endPosition) {
            throw new IllegalArgumentException();
        }
        for (int i = startPosition - 1; i < endPosition; i++) {
            // If any character equals a number below, then it will change it to the roman numeral, or add to the string
            switch (this.ram.charAt(i)) {
                case '1':
                    this.ram = this.ram.substring(0, i) + "I" + this.ram.substring(i + 1);
                    break;
                case '2':
                    this.ram = this.ram.substring(0, i) + "II" + this.ram.substring(i + 1);
                    break;
                case '3':
                    this.ram = this.ram.substring(0, i) + "III" + this.ram.substring(i + 1);
                    break;
                case '4':
                    this.ram = this.ram.substring(0, i) + "IV" + this.ram.substring(i + 1);
                    break;
                case '5':
                    this.ram = this.ram.substring(0, i) + "V" + this.ram.substring(i + 1);
                    break;
                case '6':
                    this.ram = this.ram.substring(0, i) + "VI" + this.ram.substring(i + 1);
                    break;
                case '7':
                    this.ram = this.ram.substring(0, i) + "VII" + this.ram.substring(i + 1);
                    break;
                case '8':
                    this.ram = this.ram.substring(0, i) + "VIII" + this.ram.substring(i + 1);
                    break;
                case '9':
                    this.ram = this.ram.substring(0, i) + "IX" + this.ram.substring(i + 1);
                    break;
            }
        }
    }
}
