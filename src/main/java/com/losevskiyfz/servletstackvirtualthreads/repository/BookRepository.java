package com.losevskiyfz.servletstackvirtualthreads.repository;


import com.losevskiyfz.servletstackvirtualthreads.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
