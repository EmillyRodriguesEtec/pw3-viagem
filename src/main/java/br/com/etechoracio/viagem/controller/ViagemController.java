package br.com.etechoracio.viagem.controller;

import br.com.etechoracio.viagem.entity.Viagem;
import br.com.etechoracio.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController {
    @Autowired
    private ViagemRepository repository;

    @GetMapping
    public List<Viagem> buscarTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Viagem> buscarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping //insere o body no banco de dados
    public Viagem inserir(@RequestBody Viagem body){ //body é um objeto do tipo viagem
        Viagem inserida = repository.save(body); //o save devolve o objeto do tipo viagem (body)
        return inserida;
    }

    @PutMapping("/{id}")
    public Viagem alterar(@RequestBody Long id,
                          @PathVariable Viagem obj){

        Optional<Viagem> existe = buscarPorId(id);
        if(existe.isPresent())
            repository.save(obj);
        return obj;
    }


}

