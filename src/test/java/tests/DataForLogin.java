package tests;

import model.Customer;

public class DataForLogin {
    public static Object [] [] validCustomer() {
        return new Object[][] {
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("secret_sauce").withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("secret_sauce").withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("secret_sauce").withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("secret_sauce").withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("secret_sauce").withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("secret_sauce").withBrowser("firefox").build()
                }

        };
    }
    public static Object [] [] invalidCustomer() {
        return new Object[][] {
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("error")
                                .withText("Epic sadface: Username and password do not match any user in this service")
                                .withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("standard_user").withPassword("error")
                                .withText("Epic sadface: Username and password do not match any user in this service")
                                .withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("locked_out_user").withPassword("secret_sauce")
                                .withText("Epic sadface: Sorry, this user has been locked out.")
                                .withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("locked_out_user").withPassword("secret_sauce")
                                .withText("Epic sadface: Sorry, this user has been locked out.")
                                .withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("4400298")
                                .withText("Epic sadface: Username and password do not match any user in this service")
                                .withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("problem_user").withPassword("4400298")
                                .withText("Epic sadface: Username and password do not match any user in this service")
                                .withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("")
                                .withText("Epic sadface: Password is required").withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("performance_glitch_user").withPassword("")
                                .withText("Epic sadface: Password is required").withBrowser("firefox").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("").withPassword("mdof9482")
                                .withText("Epic sadface: Username is required").withBrowser("chrome").build()
                },
                {
                        Customer.newEntity()
                                .withUsername("").withPassword("mdof9482")
                                .withText("Epic sadface: Username is required").withBrowser("firefox").build()
                },
        };
    }

}
