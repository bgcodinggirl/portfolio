package com.example.contacts.Model;

import java.util.Comparator;

public class Contact {
    int id;
    String firstName, lastName, phone, description, category;
    public  Contact(int id,String firstName,String lastName,String phone,String description,String category){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.description=description;
        this.category=category;
    }

    public  int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhone(){
        return phone;
    }

    public String getDescription(){
        return description;
    }
    public String getCategory(){
        return category;
    }

    public static Comparator<Contact> nameComparator = new Comparator<Contact>() {
        @Override
        public int compare(Contact firstContact, Contact secondContact) {
            String firstContactName= firstContact.getFirstName().toUpperCase()+" "+firstContact.getLastName().toUpperCase();
            String secondContactName= secondContact.getFirstName().toUpperCase()+" "+secondContact.getLastName().toUpperCase();
            return firstContactName.compareTo(secondContactName);
        }
    };
}
