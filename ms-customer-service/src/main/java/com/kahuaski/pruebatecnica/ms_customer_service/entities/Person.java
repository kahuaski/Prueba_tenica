package com.kahuaski.pruebatecnica.ms_customer_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_persons")
@Inheritance(strategy = InheritanceType.JOINED) 
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, length = 100)
    private String name; 
    private String gender; 
    private int age; 

    @Column(unique = true, nullable = false)
    private String address;       
    private String phone; 

    public Person() {}

    public Person(String name, String gender, int age, String identification, String address, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}