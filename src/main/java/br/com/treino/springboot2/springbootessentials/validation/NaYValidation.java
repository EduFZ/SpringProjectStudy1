package br.com.treino.springboot2.springbootessentials.validation;

import org.springframework.stereotype.Repository;

@Repository
public class NaYValidation {
    public boolean naYValidation(String str){
        String name = str.toLowerCase();
        return name.contains("y");
    }
}
