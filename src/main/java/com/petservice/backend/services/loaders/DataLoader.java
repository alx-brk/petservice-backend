package com.petservice.backend.services.loaders;

import com.petservice.backend.persistence.entity.Animal;
import com.petservice.backend.persistence.entity.City;
import com.petservice.backend.persistence.entity.PetService;
import com.petservice.backend.persistence.entity.User;
import com.petservice.backend.persistence.repository.AnimalRepository;
import com.petservice.backend.persistence.repository.CityRepository;
import com.petservice.backend.persistence.repository.PetServiceRepository;
import com.petservice.backend.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {
    private static final List<String> ANIMALS = Arrays.asList("Собаки", "Кошки", "Рыбки", "Рептилии", "Птицы", "Грызуны", "Насекомые", "Другое");
    private static final List<String> SERVICES = Arrays.asList("Передержка", "Выгул", "Кормление", "Дрессировка", "Уход за больным животным", "Стрижка");
    private static final List<String> CITIES = Arrays.asList("Санкт-Петербург", "Москва");

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PetServiceRepository petServiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
//    @PostConstruct
    public void init() {
        animalRepository.deleteAll();
        for (String name : ANIMALS) {
            Animal animal = new Animal();
            animal.setName(name);
            animalRepository.save(animal);
        }

        petServiceRepository.deleteAll();
        for (String name : SERVICES) {
            PetService petService = new PetService();
            petService.setName(name);
            petServiceRepository.save(petService);
        }

        cityRepository.deleteAll();
        for (String name : CITIES) {
            City city = new City();
            city.setName(name);
            cityRepository.save(city);
        }

        User user = new User();
        user.setEmail("masha.pupkina@gmail.com");
        user.setName("Маша Пупкина");
        user.setPhone("+79047389265");
        user.setActivePetsitter(false);

        City city = cityRepository.findByNameEquals("Санкт-Петербург");
        user.setCity(city);
        userRepository.save(user);
    }
}
