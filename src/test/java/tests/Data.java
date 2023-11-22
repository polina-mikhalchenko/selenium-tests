package tests;
import model.CheckoutInfo;
import model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Data {
    public static Object [] [] validCustomer() {
        return new Object[][] {
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("secret_sauce").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("secret_sauce").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("secret_sauce").build()
                }

        };
    }
    public static Object [] [] invalidCustomer() {
        return new Object[][] {
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("error")
                                .withText("Epic sadface: Password is required").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("locked_out_user").withPassword("secret_sauce")
                                .withText("Epic sadface: Sorry, this user has been locked out.")
                                .build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("4400298")
                                .withText("Epic sadface: Username and password do not match any user in this service")
                                .build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("")
                                .withText("Epic sadface: Password is required").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("").withPassword("mdof9482")
                                .withText("Epic sadface: Username is required").build()
                },
        };
    }
   public static Object [] [] customerInfo() {
        return new Object[][] {
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Ann").withLastName("Brown").withPostalCode("14424")
                                .withText("Thank you for your order!").build()
                },
        };
   }
    public static Object [] [] invalidCustomerInfo() {
        return new Object[][] {
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("").withLastName("Brown").withPostalCode("08765432578")
                                .withText("Error: First Name is required").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("John").withLastName("").withPostalCode("8906")
                                .withText("Error: Last Name is required").build()
                },
                {
                        CheckoutInfo.newEntity()
                                .withFirstName("Elena").withLastName("Diadumenian").withPostalCode("")
                                .withText("Error: Postal Code is required").build()
                },
        };
    }
}
