package ru.turaev.grpcserver.repository;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.turaev.grpcserver.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentRepository {
    private final List<Student> studentList = new ArrayList<>();
    private static int id = 0;

    public Mono<Student> findStudentById(long id) {
        return Mono.justOrEmpty(studentList.stream().filter(student -> student.getId() == id).findFirst());
    }

    public Flux<Student> findStudentsByName(String name) {
        return Flux.just(studentList.stream().filter(student -> student.getName().equals(name)).toArray(Student[]::new));
    }

    public Mono<Student> addNewStudent(Student student) {
        student.setId(++id);
        studentList.add(student);
        return Mono.just(student);
    }

    public Mono<Student> updateStudent(long id, Student student) {
        Optional<Student> optionalStudent = studentList.stream().filter(st-> st.getId() == id).findFirst();
        if (optionalStudent.isEmpty()) {
            return Mono.empty();
        }
        Student st = optionalStudent.get();
        st.setName(student.getName());
        st.setBirthDay(student.getBirthDay());

        return Mono.just(st);
    }

    public Mono<Student> deleteStudent(Student student) {
        Mono<Student> studentMono = findStudentById(student.getId());

        findStudentById(student.getId())
                .doOnSuccess(studentList::remove);

        return studentMono;
    }
}
