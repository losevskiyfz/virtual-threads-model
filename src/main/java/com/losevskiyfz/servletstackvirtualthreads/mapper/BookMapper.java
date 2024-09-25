package com.losevskiyfz.servletstackvirtualthreads.mapper;

import com.losevskiyfz.servletstackvirtualthreads.domain.Book;
import com.losevskiyfz.servletstackvirtualthreads.domain.PostBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book toBook(PostBookDto dto);
    PostBookDto toPostBookDto(Book bookRecord);
}
