package com.project.ecommerce.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    // tüm işlemleri ilişkili varlık üzerinde otomatik olarak gerçekleştirir
    /*
     * PERSIST: Ana varlık (parent) kaydedildiğinde, ilişkili varlıklar (child) da
     * otomatik olarak kaydedilir.
     * MERGE: Ana varlık güncellendiğinde, ilişkili varlıklar da otomatik olarak
     * güncellenir.
     * REMOVE: Ana varlık silindiğinde, ilişkili varlıklar da otomatik olarak
     * silinir.
     * REFRESH: Ana varlık yenilendiğinde, ilişkili varlıklar da yenilenir.
     * DETACH: Ana varlık oturumdan ayrıldığında, ilişkili varlıklar da ayrılır.
     */
    /*
     * CascadeType.REMOVE: Ana varlık (parent) silindiğinde, ilişkili tüm alt
     * varlıkları da siler.
     * orphanRemoval = true: Ana varlık (parent) ilişkili alt varlığı listeden
     * çıkardığında, yalnızca bu bağlantısız nesneyi (orphan) siler.
     */
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalSellingPrice;

    private int totalItem;

    private int totalMrpPrice;

    private int discount;

    private String couponCode;

}
