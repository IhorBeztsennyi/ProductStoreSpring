package ua.goit.converter;

public interface Converter<T, E> {
    E daoToDto(T type);
    T dtoToDao(E type);
}
