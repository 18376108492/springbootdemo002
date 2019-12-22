package com.itdan.springbootdemo002.dao;

import com.itdan.springbootdemo002.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//@RepositoryRestResource(path = "/bs")不想用books
public interface BookDAO extends JpaRepository<Book,Integer> {

      // @RestResource(path = "/byname",rel = "byname")
        List<Book> getAllByName(@Param(value = "name")String name);
}
