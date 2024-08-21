//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class BarcodeQRCodeReader {
//
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/decode")
//    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("cartId") String cartId) {
//        try {
//            BufferedImage image = ImageIO.read(file.getInputStream());
//
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//                    new BufferedImageLuminanceSource(image)));
//
//            Reader reader = new MultiFormatReader();
//
//            Result result = reader.decode(bitmap);
//
//            // Log the decoded result for debugging
//            System.out.println("Decoded text: " + result.getText());
//
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            Product product = objectMapper.readValue(result.getText(), Product.class);
//
////            String cartId = (String) session.getAttribute("cartId");
////            if (cartId == null) {
////                cartId = session.getCartid();
////                session.setAttribute("cartId", cartId);
////            }
//
//            // Step 6: Add product to cart
//            cartService.addItemToCart(cartId, product);
//
//            return ResponseEntity.ok("Product added to cart: " + result.getText());
//        } catch (NotFoundException e) {
//            // This block handles the case where no barcode is found in the image
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
//        } catch (IOException e) {
//            // This block handles any IO exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
//        } catch (Exception e) {
//            // This block catches all other exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
//        }
//    }
//}
//
//


//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpSession;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class BarcodeQRCodeReader {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/decode")
//    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, HttpSession session) {
//        try {
//            // Step 1: Convert MultipartFile to BufferedImage
//            BufferedImage image = ImageIO.read(file.getInputStream());
//
//            // Step 2: Prepare the image for reading helper class
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//                    new BufferedImageLuminanceSource(image)));
//
//            // Step 3: Use a reader that can decode various barcode formats
//            Reader reader = new MultiFormatReader();
//
//            // Step 4: Try to decode the barcode or QR code from the image
//            Result result = reader.decode(bitmap);
//
//            // Log the decoded result for debugging
//            System.out.println("Decoded text: " + result.getText());
//
//            // Step 5: Parse the decoded text as JSON
//            ObjectMapper objectMapper = new ObjectMapper();
//            Product product = objectMapper.readValue(result.getText(), Product.class);
//
//            // Get or create a cart for the session
//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new Cart();
//                session.setAttribute("cart", cart);
//            }
//
//            // Get the user by userId
//            User user = (User) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Step 6: Add product to cart
//            cartService.addItemToCart(cart, product, user);
//
//            return ResponseEntity.ok("Product added to cart: " + result.getText());
//        } catch (NotFoundException e) {
//            // This block handles the case where no barcode is found in the image
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
//        } catch (IOException e) {
//            // This block handles any IO exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
//        } catch (Exception e) {
//            // This block catches all other exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
//        }
//    }
//}

//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import jakarta.servlet.http.HttpSession;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class BarcodeQRCodeReaderController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/decode")
//    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, HttpSession session) {
//        try {
//            // Step 1: Convert MultipartFile to BufferedImage
//            BufferedImage image = ImageIO.read(file.getInputStream());
//
//            // Step 2: Prepare the image for reading helper class
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//                    new BufferedImageLuminanceSource(image)));
//
//            // Step 3: Use a reader that can decode various barcode formats
//            Reader reader = new MultiFormatReader();
//
//            // Step 4: Try to decode the barcode or QR code from the image
//            Result result = reader.decode(bitmap);
//
//            // Log the decoded result for debugging
//            System.out.println("Decoded text: " + result.getText());
//
//            // Get or create a cart for the session
//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new Cart();
//                cart.setCartId(cartService.generateCartId());
//                session.setAttribute("cart", cart);
//                session.setMaxInactiveInterval(2 * 60 * 60); // Set session timeout to 2 hours
//            }
//
//            // Get the user by userId
//            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Step 5: Try to parse the decoded text as JSON
//            Product product;
//            try {
//                ObjectMapper objectMapper = new ObjectMapper();
//                product = objectMapper.readValue(result.getText(), Product.class);
//            } catch (Exception e) {
//                // If the data is not in JSON format, treat it as plain text
//                product = new Product();
//                product.setRfid(result.getText());
//                product.setProductId("Unknown");
//                product.setName("Unknown");
//                product.setPrice(0.0);
//            }
//
//            // Step 6: Add product to cart
//            cartService.addItemToCart(cart, product, user);
//
//            return ResponseEntity.ok("Product added to cart: " + result.getText());
//        } catch (NotFoundException e) {
//            // This block handles the case where no barcode is found in the image
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
//        } catch (IOException e) {
//            // This block handles any IO exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
//        } catch (Exception e) {
//            // This block catches all other exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
//        }
//    }
//}


//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.ProductRepository;
//import com.reader.scanner.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import jakarta.servlet.http.HttpSession;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class BarcodeQRCodeReaderController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @PostMapping("/decode")
//    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, HttpSession session) {
//        try {
//            // Step 1: Convert MultipartFile to BufferedImage
//            BufferedImage image = ImageIO.read(file.getInputStream());
//
//            // Step 2: Prepare the image for reading helper class
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//                    new BufferedImageLuminanceSource(image)));
//
//            // Step 3: Use a reader that can decode various barcode formats
//            Reader reader = new MultiFormatReader();
//
//            // Step 4: Try to decode the barcode or QR code from the image
//            Result result = reader.decode(bitmap);
//
//            // Log the decoded result for debugging
//            System.out.println("Decoded text: " + result.getText());
//
//            // Get or create a cart for the session
//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new Cart();
//                cart.setCartId(cartService.generateCartId());
//                session.setAttribute("cart", cart);
//                session.setMaxInactiveInterval(2 * 60 * 60); // Set session timeout to 2 hours
//            }
//
//            // Get the user by userId
//            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Step 5: Try to parse the decoded text as JSON
//            ObjectMapper objectMapper = new ObjectMapper();
//            Product product = objectMapper.readValue(result.getText(), Product.class);
//
//            // Save the product to the database
//            product = productRepository.save(product);
//
//            // Step 6: Add product to cart
//            cartService.addItemToCart(cart, product, user);
//
//            return ResponseEntity.ok("Product added to cart: " + result.getText());
//        } catch (NotFoundException e) {
//            // This block handles the case where no barcode is found in the image
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
//        } catch (IOException e) {
//            // This block handles any IO exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
//        } catch (Exception e) {
//            // This block catches all other exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
//        }
//    }
//}


//package com.reader.scanner.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.*;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
//import com.reader.scanner.model.Product;
//import com.reader.scanner.model.Cart;
//import com.reader.scanner.model.User;
//import com.reader.scanner.service.CartService;
//import com.reader.scanner.repository.ProductRepository;
//import com.reader.scanner.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import jakarta.servlet.http.HttpSession;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api")
//public class BarcodeQRCodeReaderController {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @PostMapping("/decode")
//    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, HttpSession session) {
//        try {
//            // Step 1: Convert MultipartFile to BufferedImage
//            BufferedImage image = ImageIO.read(file.getInputStream());
//
//            // Step 2: Prepare the image for reading helper class
//            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
//                    new BufferedImageLuminanceSource(image)));
//
//            // Step 3: Use a reader that can decode various barcode formats
//            Reader reader = new MultiFormatReader();
//
//            // Step 4: Try to decode the barcode or QR code from the image
//            Result result = reader.decode(bitmap);
//
//            // Log the decoded result for debugging
//            System.out.println("Decoded text: " + result.getText());
//
//            // Get the user by userId
//            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//            // Get or create a cart for the session and associate it with the correct user
//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart == null || !cart.getUser().getId().equals(userId)) {
//                cart = new Cart();
//                cart.setCartId(cartService.generateCartId());
//                cart.setUser(user);
//                session.setAttribute("cart", cart);
//                session.setMaxInactiveInterval(2 * 60 * 60); // Set session timeout to 2 hours
//            }
//
//            // Step 5: Try to parse the decoded text as JSON
//            ObjectMapper objectMapper = new ObjectMapper();
//            Product product = objectMapper.readValue(result.getText(), Product.class);
//
//            // Save the product to the database
//            product = productRepository.save(product);
//
//            // Step 6: Add product to cart
//            cartService.addItemToCart(cart, product);
//
//            // Step 7: Save the cart to the database
//            cartService.saveCart(cart);
//
//            return ResponseEntity.ok("Product added to cart: " + result.getText());
//        } catch (NotFoundException e) {
//            // This block handles the case where no barcode is found in the image
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
//        } catch (IOException e) {
//            // This block handles any IO exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
//        } catch (Exception e) {
//            // This block catches all other exceptions
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
//        }
//    }
//}



/*
package com.reader.scanner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.reader.scanner.model.Product;
import com.reader.scanner.model.Cart;
import com.reader.scanner.model.User;
import com.reader.scanner.service.CartService;
import com.reader.scanner.repository.ProductRepository;
import com.reader.scanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BarcodeQRCodeReaderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/decode")
    public ResponseEntity<String> decodeBarcodeOrQRCode(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, HttpSession session) {
        try {
            // Step 1: Convert MultipartFile to BufferedImage
            BufferedImage image = ImageIO.read(file.getInputStream());

            // Step 2: Prepare the image for reading helper class
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(image)));

            // Step 3: Use a reader that can decode various barcode formats
            Reader reader = new MultiFormatReader();

            // Step 4: Try to decode the barcode or QR code from the image
            Result result = reader.decode(bitmap);

            // Log the decoded result for debugging
            System.out.println("Decoded text: " + result.getText());

            // Get the user by userId
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

            // Get or create a cart for the session and associate it with the correct user
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || !cart.getUser().getId().equals(userId)) {
                cart = new Cart();
                cart.setCartId(cartService.generateCartId());
                cart.setUser(user);
                session.setAttribute("cart", cart);
                session.setMaxInactiveInterval(2 * 60 * 60); // Set session timeout to 2 hours
            }

            // Step 5: Try to parse the decoded text as JSON
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(result.getText(), Product.class);

            // Save the product to the database
            product = productRepository.save(product);

            // Step 6: Add product to cart
            cartService.addItemToCart(cart, product);

            // Step 7: Save the cart to the database
            cartService.saveCart(cart);

            return ResponseEntity.ok("Session ID: " + session.getId() + " | Product added to cart: " + result.getText());
        } catch (NotFoundException e) {
            // This block handles the case where no barcode is found in the image
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
        } catch (IOException e) {
            // This block handles any IO exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
        } catch (Exception e) {
            // This block catches all other exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
        }
    }
}
*/

package com.reader.scanner.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.reader.scanner.model.Product;
import com.reader.scanner.model.Cart;
import com.reader.scanner.model.User;
import com.reader.scanner.service.CartService;
import com.reader.scanner.repository.ProductRepository;
import com.reader.scanner.repository.UserRepository;
import com.reader.scanner.service.ProductLookupService;
import com.reader.scanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RFIDController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductLookupService productLookupService;

    @PostMapping("/scan")
    public ResponseEntity<Map<String, String>> scanRFID(@RequestBody Map<String, String> rfidMap, HttpSession session) {
        try {
            String rfid = rfidMap.get("rfid");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).body(null);
            }

            Map<String, String> productDetails = productLookupService.getProductDetails(rfid);
            if (productDetails == null) {
                return ResponseEntity.status(404).body(null);
            }

            Product product = new Product();
            product.setRfid(rfid);
            product.setProductId(productDetails.get("productId"));
            product.setName(productDetails.get("name"));
            product.setPrice(Double.parseDouble(productDetails.get("price")));
            product.setWeight(Double.parseDouble(productDetails.get("weight")));



            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || !cart.getUser().getId().equals(user.getId())) {
                cart = new Cart();
                cart.setCartId(cartService.generateCartId());
                cart.setUser(user);
                cartService.saveCart(cart); // Save the new cart
                session.setAttribute("cart", cart);
                session.setMaxInactiveInterval(2 * 60 * 60); // Set session timeout to 2 hours
            }


//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new Cart();
//                cart.setCartId(cartService.generateCartId());
//                cart.setUser(user);
//                session.setAttribute("cart", cart);
//                session.setMaxInactiveInterval(2 * 60 * 60);
//            }

            cartService.addItemToCart(cart, product);
            cartService.saveCart(cart);

            return ResponseEntity.ok(productDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }




    @PostMapping("/decode-checkout-qr")
    public ResponseEntity<String> decodeCheckoutQR(@RequestParam("file") MultipartFile file, HttpSession session) {
        try {
            // Step 1: Convert MultipartFile to BufferedImage
            BufferedImage image = ImageIO.read(file.getInputStream());

            // Step 2: Prepare the image for reading helper class
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(image)));

            // Step 3: Use a reader that can decode various barcode formats
            Reader reader = new MultiFormatReader();

            // Step 4: Try to decode the barcode or QR code from the image
            Result result = reader.decode(bitmap);

            // Log the decoded result for debugging
            System.out.println("Invoice: " + result.getText());

            // Invalidate the session after decoding the QR code
            session.invalidate();

            return ResponseEntity.ok("Session is now over. Decoded text: " + result.getText());
        } catch (NotFoundException e) {
            // This block handles the case where no barcode is found in the image
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Barcode/QR Code not found in the image.");
        } catch (IOException e) {
            // This block handles any IO exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the image: " + e.getMessage());
        } catch (Exception e) {
            // This block catches all other exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error decoding barcode/QR: " + e.getMessage());
        }
    }
}

