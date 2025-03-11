package com.ufcg.bi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.services.synchronizationSevice.SynchronizationService;
@RestController
@RequestMapping("/synchronization")
public class SynchronizationController {
     @Autowired
     SynchronizationService synchronizationService;
    private static final String SECRET_KEY = "senha-secreta"; 

    @PostMapping("/start")
    public String startSynchronization(@RequestParam String password) {
        if (!SECRET_KEY.equals(password)) {
            return "Senha inválida";
        }

        triggerSynchronization();
        return "Sincronização iniciada em segundo plano";
    }

    @Async
    public void triggerSynchronization() {
        synchronizationService.synchronizeData();
    }
}
