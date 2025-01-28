package com.project.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotNull
    @Column(unique = true)
    private String categoryId;

    @ManyToOne
    private Category parentCategory;

    @NotNull
    private Integer level;

    /*
     * @NotNull: Bu anotasyon, genellikle doğrulama
     * (validation) amaçlı kullanılır. Yani, uygulama çalışırken bir nesnenin bu
     * alanda null olmaması gerektiğini belirtir. Bu anotasyon, genellikle kullanıcı
     * girişini kontrol etmek, veritabanına kaydetmeden önce geçerliliğini sağlamak
     * için kullanılır.
     * ******************************************************************************* *
     * @Column(nullable = false): Bu anotasyon, JPA (Java Persistence API) ve
     * Hibernate gibi ORM (Object-Relational Mapping) araçları ile veritabanı
     * tabloları üzerinde çalışırken kullanılır. nullable = false, veritabanındaki
     * ilgili sütunun null değer kabul etmeyeceğini belirtir. Yani, veritabanı
     * şemasını oluştururken bu sütunun null olamayacağı zorunlu hale gelir.
     */

}
