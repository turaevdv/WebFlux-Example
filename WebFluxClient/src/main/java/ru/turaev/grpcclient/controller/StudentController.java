package ru.turaev.grpcclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.turaev.grpcclient.model.Student;
import ru.turaev.grpcclient.webfux.client.StudentClient;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentClient studentClient;

    @GetMapping("/{id}")
    public void getStudent(@PathVariable long id) {
        studentClient.getStudent(id)
                .doOnSubscribe(student -> System.out.println("Get student by id = " + id + ": " + student));
    }

    @GetMapping
    public void listStudents(@RequestParam(name = "name", required = false) String name) {
        studentClient.listStudents(name)
                .doOnSubscribe(student -> System.out.println("One of the student list: " + student));
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        studentClient.addNewStudent(student)
                .doOnSubscribe(student1 -> System.out.println("Add new student to the student list: " + student1));
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable long id, @RequestBody Student student) {
        studentClient.updateStudent(id, student)
                .doOnSubscribe(student1 -> System.out.println("Add new student to the student list: " + student1));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentClient.deleteStudent(id)
                .doOnSubscribe(student1 -> System.out.println("Delete a student with id = " + id + ": " + student1));
    }
}