package com.tw.bootcamp.bookshop.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="books")
public class Book {
    @Id
    private Long id;

    private String name;

    private int price;

    private String authorName;

}
