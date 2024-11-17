package com.example.demo.service;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    // Добавление кофе
    public Coffee addCoffee(Coffee coffee) {
        Coffee savedCoffee = coffeeRepository.save(coffee);
        kafkaProducerService.sendMessage("Coffee added: " + savedCoffee.getName());  // Отправка сообщения в Kafka
        return savedCoffee;
    }

    // Обновление кофе
    public Coffee updateCoffee(int id, Coffee coffee) {
        coffee.setId(id);  // Устанавливаем id для обновления записи
        Coffee updatedCoffee = coffeeRepository.save(coffee);
        kafkaProducerService.sendMessage("Coffee updated: " + updatedCoffee.getName());  // Отправка сообщения в Kafka
        return updatedCoffee;
    }

    // Удаление кофе
    public void deleteCoffee(int id) {
        coffeeRepository.deleteById(id);
        kafkaProducerService.sendMessage("Coffee deleted with id: " + id);  // Отправка сообщения в Kafka
    }

    // Получение всех кофе
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    // Получение кофе по id
    public Coffee getCoffeeById(int id) {
        return coffeeRepository.findById(id).orElse(null);  // Возвращаем кофе или null, если не найдено
    }
}
