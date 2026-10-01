package br.fiap.calculadorathymeleaf.controller;

import br.fiap.calculadorathymeleaf.service.CalculadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //vai devolver uma pagina
@RequestMapping("calculadora")

public class CalculadoraController {

    private final CalculadoraService service;


    //metodo construtor
    public CalculadoraController(CalculadoraService service) {

        this.service = service;
    }


    @GetMapping("calcular")
    //o metodo calcular retorna uma pagina html q eh uma string e nao um double
    public String calcular(int a, int b, String operacao, Model model){

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operacao", operacao);

        try {

            model.addAttribute("resultado",
                    service.calcular(a, b, operacao));

        }
        catch (IllegalArgumentException e) {

            model.addAttribute("erro", e.getMessage());
        }

        return "index";
    }

}
