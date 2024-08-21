//package com.reader.scanner.model;
//
//
//import jakarta.persistence.*;
//
//@Entity
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//
//    private Long id;
//
//    private String rfid;
//    private String productId;
//    private String name;
//    private double price;
//
//    @ManyToOne
//    private Cart cart;
//
//    public CartItem() {
//    }
//
//    public CartItem(String rfid, String productId, String name, double price) {
//
//        this.rfid = rfid;
//        this.productId = productId;
//        this.name = name;
//        this.price = price;
//
//    }
//
//    public CartItem(String productId, String name, double price) {
//    }
//
//
//    // Getters and setters
//
//
//    public Long getId() {
//        return id;
//    }
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRfid() {
//        return rfid;
//    }
//
//    public void setRfid(String rfid) {
//        this.rfid = rfid;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//}
//
////
////package com.reader.scanner.model;
////
////import jakarta.persistence.*;
////
////@Entity
////public class CartItem {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    private String rfid;
////    private String productId;
////    private String name;
////    private double price;
////    private int quantity;
////
////    @ManyToOne
////    @JoinColumn(name = "cart_id")
////    private Cart cart;
////
////    public CartItem() {
////    }
////
////    public CartItem(String rfid, String productId, String name, double price, int quantity) {
////        this.rfid = rfid;
////        this.productId = productId;
////        this.name = name;
////        this.price = price;
////        this.quantity = quantity;
////    }
////
////    // Getters and setters
////
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
////
////    public String getRfid() {
////        return rfid;
////    }
////
////    public void setRfid(String rfid) {
////        this.rfid = rfid;
////    }
////
////    public String getProductId() {
////        return productId;
////    }
////
////    public void setProductId(String productId) {
////        this.productId = productId;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public double getPrice() {
////        return price;
////    }
////
////    public void setPrice(double price) {
////        this.price = price;
////    }
////
////    public int getQuantity() {
////        return quantity;
////    }
////
////    public void setQuantity(int quantity) {
////        this.quantity = quantity;
////    }
////
////    public Cart getCart() {
////        return cart;
////    }
////
////    public void setCart(Cart cart) {
////        this.cart = cart;
////    }
////}


package com.reader.scanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rfid;
    private String productId;
    private String name;
    private double price;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    public CartItem() {
    }

    public CartItem(String rfid, String productId, String name, double price, double weight ,Cart cart) {
        this.rfid = rfid;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.cart = cart;

    }

    public CartItem(String rfid, String productId, String name, double price, double weight) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
