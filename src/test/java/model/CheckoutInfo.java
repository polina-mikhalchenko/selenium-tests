package model;

public class CheckoutInfo {
    private String firstName;
    private String lastName;
    private String postalCode;
    private String text;
    public static CheckoutInfo.Builder newEntity() { return new CheckoutInfo().new Builder(); }
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPostalCode() {return postalCode;}
    public String getText() {return text;}
    public class Builder{
        private Builder() {}
        public Builder withFirstName (String firstName) {CheckoutInfo.this.firstName = firstName; return this;}
        public Builder withLastName (String lastName) {CheckoutInfo.this.lastName = lastName; return this;}
        public Builder withPostalCode (String postalCode) {CheckoutInfo.this.postalCode = postalCode; return this;}
        public Builder withText (String text) {CheckoutInfo.this.text = text; return this;}
        public CheckoutInfo build() {return CheckoutInfo.this;}
    }
}
