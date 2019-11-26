package com.petservice.backend.services.loaders;

import com.petservice.backend.persistence.entity.*;
import com.petservice.backend.persistence.enums.JobStatus;
import com.petservice.backend.persistence.enums.Units;
import com.petservice.backend.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Transactional
    @PostConstruct
    public void init() {

        for (String name : ANIMALS) {
            Animal animal = new Animal();
            animal.setName(name);
            animalRepository.save(animal);
        }

        for (String name : SERVICES) {
            PetService petService = new PetService();
            petService.setName(name);
            petServiceRepository.save(petService);
        }

        for (String name : CITIES) {
            City city = new City();
            city.setName(name);
            cityRepository.save(city);
        }

        User user1 = new User();
        user1.setEmail("masha.pupkina@gmail.com");
        user1.setName("Маша Пупкина");
        user1.setPhone("+79047389265");
        user1.setActivePetsitter(true);

        City city1 = cityRepository.findByNameEquals("Санкт-Петербург");
        user1.setCity(city1);

        Animal animal1 = animalRepository.findByNameEquals("Собаки");
        user1.setAnimals(Collections.singleton(animal1));

        Catalog catalog1 = new Catalog();
        catalog1.setPetService(petServiceRepository.findByNameEquals("Выгул"));
        catalog1.setPetsitter(user1);
        catalog1.setPrice(500);
        catalog1.setUnits(Units.RUB);
        user1.setCatalogSet(Collections.singleton(catalog1));
        user1 = userRepository.save(user1);
        catalog1 = catalogRepository.save(catalog1);

        User user2 = new User();
        user2.setEmail("vasya@gmail.com");
        user2.setName("Вася");
        user2.setPhone("+79048382399");
        user2.setActivePetsitter(false);

        City city2 = cityRepository.findByNameEquals("Санкт-Петербург");
        user2.setCity(city2);

        Animal animal2 = animalRepository.findByNameEquals("Кошки");
        user2.setAnimals(Collections.singleton(animal2));

        Catalog catalog2 = new Catalog();
        catalog2.setPetService(petServiceRepository.findByNameEquals("Передержка"));
        catalog2.setPetsitter(user2);
        catalog2.setPrice(600);
        catalog2.setUnits(Units.RUB_DAY);
        user2.setCatalogSet(Collections.singleton(catalog2));
        userRepository.save(user2);
        catalog2 = catalogRepository.save(catalog2);

        Job job1 = new Job();
        job1.setClient(user1);
        job1.setCity(city1);
        job1.setAnimals(Collections.singleton(animal2));
        job1.setPetServices(Collections.singleton(catalog2.getPetService()));
        job1.setJobStatus(JobStatus.NEW);
        job1.setCreationDate(LocalDate.now());
        job1.setStartDate(LocalDate.now());
        job1.setEndDate(LocalDate.now().plusDays(5));
        job1.setDescription("Take my pussy!");
        jobRepository.save(job1);

        Job job2 = new Job();
        job2.setClient(user2);
        job2.setPetsitter(user1);
        job2.setCity(city2);
        job2.setAnimals(Collections.singleton(animal1));
        job2.setPetServices(Collections.singleton(catalog1.getPetService()));
        job2.setJobStatus(JobStatus.PERFORMED);
        job2.setCreationDate(LocalDate.now());
        job2.setStartDate(LocalDate.now());
        job2.setDescription("Look at my dog. My dog is amazing!");
        jobRepository.save(job2);
    }
}
