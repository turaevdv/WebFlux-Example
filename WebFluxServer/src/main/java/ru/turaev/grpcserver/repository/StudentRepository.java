package ru.turaev.grpcserver.repository;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.turaev.grpcserver.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentRepository {
    private final List<Student> studentList = new ArrayList<>();

    public Mono<Student> findStudentById(long id) {
        return Mono.justOrEmpty(studentList.stream().filter(student -> student.getId() == id).findFirst());
    }

    public Flux<Student> findStudentsByName(String name) {
        return Flux.just(studentList.stream().filter(student -> student.getName().equals(name)).toArray(Student[]::new));
    }

    public Mono<Student> addNewStudent(Student student) {
        return Mono.empty();
    }

    public Mono<Student> updateStudent(long id, Student student) {
        return Mono.empty();
    }

    public Mono<Student> deleteStudent(Student student) {
        return Mono.empty();
    }
}
