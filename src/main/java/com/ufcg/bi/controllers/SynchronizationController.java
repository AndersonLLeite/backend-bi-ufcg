package com.ufcg.bi.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.bi.services.synchronizationSevice.SynchronizationService;


import org.slf4j.Logger;
@RestController
@RequestMapping("/synchronization")
public class SynchronizationController {
     private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationController.class);
     @Autowired
     SynchronizationService synchronizationService;
    private static final String SECRET_KEY = "senha-secreta"; // Senha definida aqui

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
