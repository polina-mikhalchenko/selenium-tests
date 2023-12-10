package tests;

import model.CheckoutInfo;

public class DataForCheckout {
    public static Object [] [] customerInfo() {
        return new Object[][] {
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Ann").withLastName("Brown").withPostalCode("14424")
                                .withText("Thank you for your order!").withBrowser("chrome").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Ann").withLastName("Brown").withPostalCode("14424")
                                .withText("Thank you for your order!").withBrowser("firefox").build()
                },
        };
    }
    public static Object [] [] invalidCustomerInfo() {
        return new Object[][] {
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("").withLastName("Brown").withPostalCode("08765432578")
                                .withText("Error: First Name is required").withBrowser("chrome").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("").withLastName("Brown").withPostalCode("08765432578")
                                .withText("Error: First Name is required").withBrowser("firefox").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("John").withLastName("").withPostalCode("8906")
                                .withText("Error: Last Name is required").withBrowser("chrome").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("John").withLastName("").withPostalCode("8906")
                                .withText("Error: Last Name is required").withBrowser("firefox").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Elena").withLastName("Diadumenian").withPostalCode("")
                                .withText("Error: Postal Code is required").withBrowser("chrome").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Elena").withLastName("Diadumenian").withPostalCode("")
                                .withText("Error: Postal Code is required").withBrowser("firefox").build()
                },
        };
    }
}
