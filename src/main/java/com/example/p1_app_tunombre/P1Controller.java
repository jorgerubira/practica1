/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.p1_app_tunombre;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class P1Controller {
    
    @Value(value = "${alumno}")
    private String alumno;
    
    @Value(value = "${server.port}")
    private String port;

    @Autowired
    private IPersonaRepository repoPersona;
    
    @GetMapping
    public String listado(Model m){
        m.addAttribute("alumno", alumno);
        m.addAttribute("port", port);
        return "listado";
    }
    
    @GetMapping("list_data")
    @ResponseBody
    public List<Persona> list_data(){
        return repoPersona.findAll();
    }
    
    @GetMapping("delete")
    @ResponseBody
    public String delete(Long id){
        repoPersona.deleteById(id);
        return "ok";
    }

    @GetMapping("new")
    @ResponseBody
    public String nuevo(String nombre){
        Persona p=new Persona();
        p.setNombre(nombre);
        repoPersona.save(p);
        return "ok";
    }

    
    
}
