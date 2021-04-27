package com.agacorpo.coronaTracker.controllers;

import com.agacorpo.coronaTracker.models.LocationStats;
import com.agacorpo.coronaTracker.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaDataService coronaDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaDataService.getStats();
        int sumTotalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int sumNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
        model.addAttribute("locationStats", coronaDataService.getStats());
        model.addAttribute("totalCases", sumTotalCases);
        model.addAttribute("newCases", sumNewCases);
        return "home";
    }
}
