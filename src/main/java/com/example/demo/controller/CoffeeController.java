package com.example.demo.controller;

import com.example.demo.model.Coffee;
import com.example.demo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    // Метод для отображения всех кофе (для страницы меню)
    @GetMapping
    public String getAllCoffees(Model model) {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        model.addAttribute("coffees", coffees);  // Добавляем список кофе в модель для отображения на странице
        return "coffees";  // Возвращает имя HTML-шаблона (coffees.html)
    }

    // Метод для добавления нового кофе
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("coffee", new Coffee());  // Создаем пустой объект Coffee для формы добавления
        return "add-coffee";  // Шаблон для добавления нового кофе
    }

    @PostMapping("/add")
    public String addCoffee(@ModelAttribute Coffee coffee) {
        coffeeService.addCoffee(coffee);  // Добавляем новый кофе
        return "redirect:/coffees";  // Перенаправляем на страницу с кофе
    }

    // Метод для обновления кофе
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Coffee coffee = coffeeService.getCoffeeById(id);  // Получаем кофе по id
        if (coffee != null) {
            model.addAttribute("coffee", coffee);  // Добавляем кофе в модель для отображения в форме
            return "update-coffee";  // Шаблон для обновления кофе
        }
        return "redirect:/coffees";  // Если кофе не найдено, перенаправляем на страницу с кофе
    }

    @PostMapping("/update/{id}")
    public String updateCoffee(@PathVariable int id, @ModelAttribute Coffee coffee) {
        coffeeService.updateCoffee(id, coffee);  // Обновляем кофе по id
        return "redirect:/coffees";  // Перенаправляем на страницу с кофе
    }

    // Метод для удаления кофе
    @GetMapping("/delete/{id}")
    public String deleteCoffee(@PathVariable int id) {
        coffeeService.deleteCoffee(id);  // Удаляем кофе по id
        return "redirect:/coffees";  // Перенаправляем на страницу с кофе
    }
}
