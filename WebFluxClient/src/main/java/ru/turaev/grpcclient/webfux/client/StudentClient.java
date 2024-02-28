package ru.turaev.grpcclient.webfux.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.turaev.grpcclient.model.Student;

@Component
@RequiredArgsConstructor
public class StudentClient {
    private final WebClient.Builder webClientBuilder;
    private static final String BASE_URL = "http://localhost:8082";

    private final String URI = "/students";

    public Mono<Student> getStudent(long id) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .get()
                .uri(URI + "/{id}", id)
                .retrieve()
                .bodyToMono(Student.class);
    }

    public Flux<Student> listStudents(String name) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URI)
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToFlux(Student.class);
    }

    public Mono<Student> addNewStudent(Student student) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .post()
                .uri(URI)
                .bodyValue(student)
                .exchangeToMono(res -> res.bodyToMono(Student.class));
    }

    public Mono<Student> updateStudent(long id, Student student) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .put()
                .uri(URI + "/{id}", id)
                .body(student, Student.class)
                .exchangeToMono(res -> res.bodyToMono(Student.class));
    }

    public Mono<Void> deleteStudent(long id) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .delete()
                .uri(URI + "/{id}", id)
                .exchangeToMono(res -> res.bodyToMono(Void.class));
    }
}
