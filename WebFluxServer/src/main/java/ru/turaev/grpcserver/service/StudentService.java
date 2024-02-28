package ru.turaev.grpcserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.turaev.grpcserver.model.Student;
import ru.turaev.grpcserver.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Mono<Student> findStudentById(long id) {
        return studentRepository.findStudentById(id);
    }

    public Flux<Student> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name);
    }

    public Mono<Student> addNewStudent(Student student) {
        return studentRepository.addNewStudent(student);
    }

    public Mono<Student> updateStudent(long id, Student student) {
        return studentRepository.updateStudent(id, student);
    }

    public Mono<Student> deleteStudent(Student student) {
        return studentRepository.deleteStudent(student);
    }
}
