package vstu.practice.book_catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vstu.practice.book_catalog.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}