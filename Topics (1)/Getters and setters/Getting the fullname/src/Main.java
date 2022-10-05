class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        if (firstName != null) this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        // write your code here
        if (lastName != null) this.lastName = lastName;
    }

    public String getFullName() {
        if (firstName.isEmpty() && lastName.isEmpty())
            return "Unknown";
        if (lastName.isEmpty())
            return firstName;
        if (firstName.isEmpty())
            return lastName;

        return firstName + " " + lastName; // write your code here
    }
}