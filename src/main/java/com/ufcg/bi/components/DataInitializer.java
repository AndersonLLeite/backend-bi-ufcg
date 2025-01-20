package com.ufcg.bi.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ufcg.bi.models.Data2;
import com.ufcg.bi.services.DataService2;

import java.util.List;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataService2 dataService;

    public DataInitializer(DataService2 dataService) {
        this.dataService = dataService;
    }

    @Override
    public void run(String... args) {
        Map<String, Double> genderDist1 = Map.of("MASCULINO", 50.0, "FEMININO", 45.0);
        Map<String, Double> ageDist1 = Map.of("16-18", 2.0, "25-27", 11.0, "22-24", 45.0, "19-21", 122.0, "31+", 27.0, "28-30", 8.0);
        Map<String, Double> affPolicyDist1 = Map.of("Bon. estadual", 61.0, "L1", 20.0, "L2", 31.0, "L5", 13.0, "L6", 33.0, "-", 57.0);

        Data2 data1 = new Data2(null, 101, "Computer Science", "Active", 1, "IT Department", 1, "Main Campus", "2023.1", genderDist1, ageDist1, affPolicyDist1);

        Map<String, Double> genderDist2 = Map.of("MASCULINO", 70.0, "FEMININO", 30.0);
        Map<String, Double> ageDist2 = Map.of("16-18", 5.0, "25-27", 15.0, "22-24", 50.0, "19-21", 100.0, "31+", 30.0, "28-30", 10.0);
        Map<String, Double> affPolicyDist2 = Map.of("Bon. estadual", 70.0, "L1", 25.0, "L2", 20.0, "L5", 10.0, "L6", 35.0, "-", 40.0);

        Data2 data2 = new Data2(null, 102, "Mathematics", "Active", 2, "Math Department", 1, "Main Campus", "2023.1", genderDist2, ageDist2, affPolicyDist2);

        dataService.saveAll(List.of(data1, data2));
    }
}
