package model;

public class Customer {
    private String username;
    private String password;
    private String text;
    private String browser;
    public static Builder newEntity() { return new Customer().new Builder(); }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getText() {return text;}
    public String getBrowser() {return browser;}
    public class Builder{
        private Builder(){}
        public Builder withUsername (String username) {Customer.this.username = username; return this;}
        public Builder withPassword (String password) {Customer.this.password = password; return this;}
        public Builder withText (String text) {Customer.this.text = text; return this;}
        public Builder withBrowser (String browser) {Customer.this.browser = browser; return this;}
        public Customer build() {return Customer.this;}
    }
}
