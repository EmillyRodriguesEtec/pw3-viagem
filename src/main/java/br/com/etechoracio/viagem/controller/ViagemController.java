package br.com.etechoracio.viagem.controller;

import br.com.etechoracio.viagem.entity.Viagem;
import br.com.etechoracio.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id)
    {
        Optional<Viagem> existe = repository.findById(id);
        if (existe.isPresent())
        {
            repository.getById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id ) { // ? ou object (mostra que não se sabe o que está
        Optional<Viagem> existe = buscarPorId(id);                  // dentro do optional, então aceita qualquer coisa)
        if (existe.isPresent())
        {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }
}

